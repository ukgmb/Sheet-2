package sheetfive.commands;

import sheetfive.game.HuntForMisterX;
import sheetfive.exceptions.InvalidCommandSyntaxException;

/**
 * Represents the start command.
 *
 * @author ukgmb
 */
public class StartCommand extends Command {
    private static final String PATTERN = "^start\\s[A,E,I,S,X][1-3][R,V]$";

    /**
     * Constructs a new start command.
     * @param game The current game played.
     */
    public StartCommand(HuntForMisterX game) {
        super(new InvalidCommandSyntaxException(), PATTERN, game);
    }

    /**
     * Executes the start command.
     * @param arguments Command arguments
     */
    public void execute(String[] arguments) {
        System.out.println("Start command success");
    }
}
