package sheetfive.exceptions;

/**
 * This exception is thrown when a player wants to pass but hasn't placed his Mister X yet.
 *
 * @author ukgmb
 */
public class InvalidPassException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Error, place Mister X before passing.";
    /**
     * Constructs a new invalid pass exception.
     */
    public InvalidPassException() {
        super(ERROR_MESSAGE);
    }
}
