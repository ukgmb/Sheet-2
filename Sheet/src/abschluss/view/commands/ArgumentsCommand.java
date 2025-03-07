package abschluss.view.commands;

import abschluss.model.Action;
import abschluss.model.Game;
import abschluss.model.Monster;
import abschluss.view.InvalidArgumentException;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the arguments of a {@link Command}.
 *
 * @author ukgmb
 */
public class ArgumentsCommand {

    private static final String ERROR_MESSAGE_TOO_FEW_ARGUMENTS = "too few arguments provided.";
    private static final String ERROR_MESSAGE_INVALID_SHOW_PARAMETER = "invalid parameter for show";
    private static final String ERROR_MESSAGE_MONSTER_NOT_FOUND = "monster '%s' wasn't declared.";
    private static final String ERROR_MESSAGE_MINIMUM_2_MONSTERS = "min 2 monsters are required to start a new "
            + "competition.";
    private static final String ERROR_MESSAGE_MONSTER_NEEDED = "the action '%s' needs a target monster.";
    private static final String ERROR_MESSAGE_ACTION_NOT_FOUND = "action '%s' was not found.";
    private static final int MINIMUM_FOR_COMPETITION = 2;

    private static final String DELIMITER_WORD_SEPARATOR = " ";

    private final List<String> arguments;
    private final Game game;

    /**
     * Constructs a new instance of arguments of effects.
     *
     * @param arguments The provided arguments
     * @param game The game currently running
     */
    public ArgumentsCommand(String arguments, Game game) {
        if (arguments == null) {
            this.arguments = new ArrayList<>();
        } else {
            String[] split = arguments.split(DELIMITER_WORD_SEPARATOR);
            this.arguments = new LinkedList<>(Arrays.asList(split));
        }
        this.game = game;
    }

    /**
     * Retrieves an argument from the given arguments.
     * @return The retrieved argument
     * @throws InvalidArgumentException if too few arguments were provided
     */
    protected String retrieveArgument() throws InvalidArgumentException {
        if (isExhausted()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }
        return this.arguments.remove(0);
    }

    /**
     * Returns whether all arguments have been parsed.
     *
     * @return {@code true}, if all arguments have been parsed. Else, returns {@code false}
     */
    public boolean isExhausted() {
        return this.arguments.isEmpty();
    }

    /**
     * Parses a path of a file.
     * @return The path of the file, if found
     * @throws InvalidArgumentException is parsing fails
     */
    protected Path parsePath() throws InvalidArgumentException {
        return Path.of(retrieveArgument());
    }

    /**
     * Parses the parameter for the {@link CommandShow}.
     * @return The parameter, if found. If no parameter was given, returns {@code null}
     * @throws InvalidArgumentException if parsing fails
     */
    protected ShowParameter parseParameter() throws InvalidArgumentException {
        if (isExhausted()) {
            return null;
        }

        String argument = retrieveArgument();
        for (ShowParameter parameter : ShowParameter.values()) {
            if (parameter.matches(argument)) {
                return parameter;
            }
        }

        throw new InvalidArgumentException(ERROR_MESSAGE_INVALID_SHOW_PARAMETER);
    }

    /**
     * Parses list of monster to start new competition for the {@link CommandCompetition}.
     * @return List of monsters, if all found
     * @throws InvalidArgumentException if parsing fails
     */
    protected List<Monster> parseParticipants() throws InvalidArgumentException {
        if (this.arguments.size() < MINIMUM_FOR_COMPETITION) {
            throw new InvalidArgumentException(ERROR_MESSAGE_MINIMUM_2_MONSTERS);
        }
        List<Monster> list = new ArrayList<>();
        while (!isExhausted()) {
            String argument = retrieveArgument();
            Monster representation = this.game.getMonster(argument);
            if (representation == null) {
                throw new InvalidArgumentException(ERROR_MESSAGE_MONSTER_NOT_FOUND.formatted(argument));
            }
            list.add(representation);
        }
        return list;
    }

    /**
     * Parses the action to be played by the current monster in the competition.
     * @return The action to be played
     * @throws InvalidArgumentException if parsing fails
     */
    protected Action parseAction() throws InvalidArgumentException {
        String argument = retrieveArgument();
        Action action = this.game.getAction(argument);

        if (action == null) {
            throw new InvalidArgumentException(ERROR_MESSAGE_ACTION_NOT_FOUND.formatted(argument));
        }
        return action;
    }

    /**
     * Parses the target monster of the action. Doesn't always require user's input.
     * @param action The action the monster plays
     * @return The target monster, if possible
     * @throws InvalidArgumentException if parsing fails
     */
    protected Monster parseTargetMonster(Action action) throws InvalidArgumentException {
        if (isExhausted() && action.needsOpponent()) {
            Monster monster = this.game.getCompetition().searchMonster();
            if (monster == null) {
                throw new InvalidArgumentException(ERROR_MESSAGE_MONSTER_NEEDED.formatted(action.getName()));
            }
            return monster;
        }
        if (isExhausted()) {
            return this.game.getCompetition().getCurrent();
        }
        String argument = retrieveArgument();
        Monster monster = this.game.getCompetition().getMonster(argument);
        if (monster == null) {
            throw new InvalidArgumentException(ERROR_MESSAGE_MONSTER_NOT_FOUND.formatted(argument));
        }
        return monster;
    }

}
