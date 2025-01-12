package sheetfive.commands;

import sheetfive.HuntForMisterX;
import sheetfive.exceptions.InvalidCommandException;

/**
 * Command Class acts like a base for all commands.
 *
 * @author ukgmb
 */
public abstract class Command {

    protected final String regex;
    private InvalidCommandException exception;
    private final HuntForMisterX game;

    /**
     * Constructs a new command of any type with exception.
     *
     * @param exception The exception which should be thrown, if command can't be executed
     * @param regex The syntax of a command
     * @param game The current game played
     */
    public Command(InvalidCommandException exception, String regex, HuntForMisterX game) {
        this.exception = exception;
        this.regex = regex;
        this.game = game;
    }

    /**
     * Executes the command.
     * @param arguments Command arguments
     */
    public abstract void execute(String[] arguments);

    /**
     * Returns the regex of the command.
     * @return The regex of the command
     */
    public String getRegex() {
        return this.regex;
    }

    /**
     * Returns the exception to the command.
     * @return The command exception
     */
    public InvalidCommandException getException() {
        return this.exception;
    }



}
