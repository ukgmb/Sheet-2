package sheetfive.commands;

import sheetfive.game.HuntForMisterX;

/**
 * Represent the quit command to quit the game.
 *
 * @author ukgmb
 */
public class QuitCommand extends Command {

    private static final String PATTERN = "quit";

    /**
     * Constructs a new quit command.
     * @param game The current game to be played
     */
    public QuitCommand(HuntForMisterX game) {
        super(null, PATTERN, game);
    }

    /**
     * Executes the quit command.
     * @param arguments Command arguments
     */
    public void execute(String[] arguments) {
        super.game.quit();
    }
}
