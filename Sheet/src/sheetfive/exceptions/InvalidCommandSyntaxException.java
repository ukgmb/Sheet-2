package sheetfive.exceptions;

/**
 * This exception is thrown when the syntax of the entered command is wrong.
 *
 * @author ukgmb
 */
public class InvalidCommandSyntaxException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Error, the entered command does not exist.";
    /**
     * Constructs a new invalid command syntax exception.
     */
    public InvalidCommandSyntaxException() {
        super(ERROR_MESSAGE);
    }
}
