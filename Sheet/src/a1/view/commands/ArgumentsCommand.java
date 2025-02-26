package a1.view.commands;

import a1.view.InvalidArgumentException;
import a1.view.configurator.ArgumentsConfiguration;

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

    private static final String DELIMITER_WORD_SEPARATOR = " ";

    private final List<String> arguments;

    /**
     * Constructs a new instance of arguments of effects.
     *
     * @param arguments The provided arguments
     */
    public ArgumentsCommand(String arguments) {
        if (arguments == null) {
            this.arguments = new ArrayList<>();
        } else {
            String[] split = arguments.split(DELIMITER_WORD_SEPARATOR);
            this.arguments = new LinkedList<>(Arrays.asList(split));
        }
    }

    private String retrieveArgument() throws InvalidArgumentException {
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
    public Path parsePath() throws InvalidArgumentException {
        return Path.of(retrieveArgument());
    }

    /**
     * Parses the parameter for the {@link CommandShow}.
     * @return The parameter, if found. If no parameter was given, returns {@code null}
     * @throws InvalidArgumentException if parsing fails
     */
    public ShowParameter parseParameter() throws InvalidArgumentException {
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

}
