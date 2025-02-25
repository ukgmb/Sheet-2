package a1.view;

import a1.model.effects.Effect;
import a1.model.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * This class represents the arguments of a {@link Command}.
 *
 * @author ukgmb
 */
public class ArgumentsCommand {

    private static final String ERROR_MESSAGE_TOO_FEW_ARGUMENTS = "too few arguments provided.";
    private static final String DELIMITER_WHITESPACE = " ";

    private List<String> arguments;
    private List<String> line;

    /**
     * Constructs a new instance of arguments.
     * @param arguments The provided arguments.
     */
    public ArgumentsCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns whether all arguments have been parsed.
     *
     * @return {@code true}, if all arguments have been parsed. Else, returns {@code false}
     */
    public boolean isExhausted() {
        return this.arguments.isEmpty() && this.line.isEmpty();
    }

    private boolean lineIsExhausted() {
        return this.line.isEmpty();
    }

    private String retrieveArgument() throws InvalidArgumentException {
        if (isExhausted()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }
        if (!this.line.isEmpty()) {
            return this.line.remove(0);
        }
        String[] line = this.arguments.remove(0).split(DELIMITER_WHITESPACE);
        this.line = new ArrayList<>(Arrays.asList(line));
        return this.line.remove(0);

    }

    /**
     * Parses the name of an action.
     * @return Name of the action
     * @throws InvalidArgumentException if parsing failed
     */
    public String parseName() throws InvalidArgumentException {
        return retrieveArgument();
    }

    /**
     * Parses the Element.
     * @return Corresponding element, if found
     * @throws InvalidArgumentException if parsing failed
     */
    public Element parseElement() throws InvalidArgumentException{
        ArgumentsEffect provider = new ArgumentsEffect(retrieveArgument());

        return provider.parseEnumValue(false, Element.values());
    }

    /**
     * Parses a list of effects.
     * @return A list of effects, if found
     * @throws InvalidArgumentException if parsing failed
     */
    public Collection<Effect> parseEffects() throws InvalidArgumentException {
        return new ArrayList<>();
    }




}
