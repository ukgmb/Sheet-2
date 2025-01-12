package sheetfive.exceptions;

/**
 * This exception is thrown when the placement of a tile is not allowed.
 *
 * @author ukgmb
 */
public class WrongPlaceException extends InvalidCommandException {
    private static final String ERROR_MESSAGE = "Error, placement not allowed.";

    /**
     * Constructs a new wrong place exception.
     */
    public WrongPlaceException() {
        super(ERROR_MESSAGE);
    }
}
