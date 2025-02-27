package abschluss.view.commands;

import abschluss.view.UserInteraction;
import abschluss.view.Result;
/**
 * Represents the command to quit the game. Requires no other arguments.
 *
 * @author ukgmb
 */
public class CommandQuit implements Command<UserInteraction> {

    /**
     * Quites the user's interaction and quits the game.
     * @param handle the user interaction to be quit
     * @return {@code null}
     */
    @Override
    public Result execute(UserInteraction handle) {
        handle.quit();
        return null;
    }
}
