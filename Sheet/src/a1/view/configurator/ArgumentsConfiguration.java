package a1.view.configurator;

import a1.model.Action;
import a1.model.effects.Effect;
import a1.model.Element;
import a1.view.commands.Command;
import a1.view.InvalidArgumentException;

import java.util.*;

/**
 * This class represents the arguments of a {@link ConfigKeyword}.
 *
 * @author ukgmb
 */
public class ArgumentsConfiguration {

    private static final String ERROR_MESSAGE_TOO_FEW_ARGUMENTS = "too few arguments provided.";
    private static final String ERROR_MESSAGE_EFFECT_NOT_FOUND = "%s effect not found.";
    private static final String ERROR_MESSAGE_INVALID_POS_NUM = "monster stats should be positive integers."
            + " But provided %s";
    private static final String ERROR_MESSAGE_ACTION_NOT_DECLARED = "action %s wasn't declared before.";
    private static final String ERROR_MESSAGE_REPEAT_IN_REPEAT = "repeat inside another repeat effect isn't allowed";
    private static final String ERROR_MESSAGE_TOO_MANY_ARGUMENTS = "provided too many arguments";
    private static final String DELIMITER_WHITESPACE = " ";
    private static final int MAXIMUM_NUMBER_OF_ACTIONS_FOR_MONSTERS = 4;
    private static final int ACTION_COUNT_START_VALUE = 0;
    private static final String END_REPEAT_EFFECT = "end repeat";

    private final List<String> arguments;
    private List<String> nextLine;
    private final List<Action> declaredActions;


    /**
     * Constructs a new instance of arguments.
     *
     * @param arguments       The provided arguments.
     * @param declaredActions All currently declared actions
     */
    public ArgumentsConfiguration(List<String> arguments, List<Action> declaredActions) {
        this.arguments = arguments;
        this.nextLine = new ArrayList<>();
        this.declaredActions = declaredActions;
    }

    /**
     * Returns whether all arguments have been parsed.
     *
     * @return {@code true}, if all arguments have been parsed. Else, returns {@code false}
     */
    public boolean isExhausted() {
        return this.arguments.isEmpty() && this.nextLine.isEmpty();
    }

    private boolean lineIsExhausted() {
        return this.nextLine.isEmpty();
    }

    private String retrieveArgument() throws InvalidArgumentException {
        if (isExhausted()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }
        if (!this.nextLine.isEmpty()) {
            return this.nextLine.remove(0);
        }
        String[] line = this.arguments.remove(0).split(DELIMITER_WHITESPACE);
        this.nextLine = new ArrayList<>(Arrays.asList(line));
        return this.nextLine.remove(0);

    }

    private String retrieveLine() throws InvalidArgumentException {
        if (!lineIsExhausted() || this.arguments.isEmpty()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }

        return this.arguments.remove(0);
    }

    /**
     * Parses the name of an action.
     *
     * @return Name of the action
     * @throws InvalidArgumentException if parsing failed
     */
    public String parseName() throws InvalidArgumentException {
        return retrieveArgument();
    }

    /**
     * Parses the Element.
     *
     * @return Corresponding element, if found
     * @throws InvalidArgumentException if parsing failed
     */
    public Element parseElement() throws InvalidArgumentException {
        ArgumentsEffect provider = new ArgumentsEffect(retrieveArgument(), this);

        return provider.parseEnumValue(false, Element.values());
    }

    /**
     * Parses a list of effects.
     *
     * @return A list of effects, if found
     * @throws InvalidArgumentException if parsing failed
     */
    public List<Effect> parseEffects() throws InvalidArgumentException {
        List<Effect> list = new ArrayList<>();
        while (!isExhausted()) {
            String[] split = retrieveLine().trim().split(DELIMITER_WHITESPACE, 2);
            String effect = split[0];
            ArgumentsEffect argumentsEffect = new ArgumentsEffect(split[1], this);
            KeywordEffect keyword = retrieveEffectKeyword(effect);
            list.add(keyword.provide(argumentsEffect));
            if (!argumentsEffect.isExhausted()) {
                throw new InvalidArgumentException(ERROR_MESSAGE_TOO_MANY_ARGUMENTS);
            }
        }
        return list;

    }

    private KeywordEffect retrieveEffectKeyword(String effect) throws InvalidArgumentException {
        for (KeywordEffect keyword : KeywordEffect.values()) {
            if (keyword.matches(effect)) {
                return keyword;
            }
        }

        throw new InvalidArgumentException(ERROR_MESSAGE_EFFECT_NOT_FOUND.formatted(effect));
    }

    /**
     * Parses a positive integer.
     *
     * @return Positive integer, if found
     * @throws InvalidArgumentException if parsing failed
     */
    public int parsePosInt() throws InvalidArgumentException {
        String argument = retrieveArgument();

        int value;
        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(ERROR_MESSAGE_INVALID_POS_NUM.formatted(argument));
        }
        if (value > 0) {
            return value;
        }
        throw new InvalidArgumentException(ERROR_MESSAGE_INVALID_POS_NUM.formatted(argument));
    }

    /**
     * Parses the actions.
     *
     * @return A set of maximum 4 actions, if found.
     * @throws InvalidArgumentException if parsing failed
     */
    public Set<Action> parseActions() throws InvalidArgumentException {
        if (lineIsExhausted()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }
        Set<Action> actions = new HashSet<>();
        int count = ACTION_COUNT_START_VALUE;
        while (!lineIsExhausted() && count < MAXIMUM_NUMBER_OF_ACTIONS_FOR_MONSTERS) {
            String argument = retrieveArgument();
            Action retrieved = retrieveAction(argument);
            if (retrieved == null) {
                throw new InvalidArgumentException(ERROR_MESSAGE_ACTION_NOT_DECLARED.formatted(argument));
            }
            actions.add(retrieved);
            count++;
        }

        return actions;

    }

    private Action retrieveAction(String nameOfAction) {
        for (Action action : this.declaredActions) {
            if (action.getName().equals(nameOfAction)) {
                return action;
            }
        }
        return null;
    }

    /**
     * Parses a list of effects except the repeat effect.
     * @return List of effects to be repeated
     * @throws InvalidArgumentException if parsing fails, or repeat is demanded
     */
    public List<Effect> parseRepeatEffects() throws InvalidArgumentException {
        List<Effect> list = new ArrayList<>();

        while (!isExhausted()) {
            String argument = retrieveLine().trim();
            if (argument.equals(END_REPEAT_EFFECT)) {
                break;
            }
            String[] split = argument.split(DELIMITER_WHITESPACE, 2);
            String effect = split[0];
            ArgumentsEffect argumentsEffect = new ArgumentsEffect(split[1], this);
            KeywordEffect keyword = retrieveEffectKeyword(effect);
            if (keyword == KeywordEffect.REPEAT) {
                throw new InvalidArgumentException(ERROR_MESSAGE_REPEAT_IN_REPEAT);
            }
            list.add(keyword.provide(argumentsEffect));

        }

        return list;
    }


}
