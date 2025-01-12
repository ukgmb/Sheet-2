package sheetfive.exceptions;

/**
 * This exception is thrown when the move command can't be executed as demanded.
 *
 * @author ukgmb
 */
public class WrongMoveException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Error, move not allowed";

    /**
     * Constructs a new wrong move exception.
     */
    public WrongMoveException() {
        super(ERROR_MESSAGE);
    }
}
