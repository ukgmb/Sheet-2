package sheetfive.exceptions;

/**
 * This abstract class represents any kind of exception if an entered command is invalid for some reason.
 *
 * @author ukgmb
 */
public abstract class InvalidCommandException extends Exception {
    /**
     * Constructs a new invalid command exception.
     * @param message The error message to be printed
     */
    protected InvalidCommandException(String message) {
        super(message);
    }
}
