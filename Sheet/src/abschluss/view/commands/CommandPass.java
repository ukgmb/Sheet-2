package abschluss.view.commands;

import abschluss.model.Competition;
import abschluss.view.Result;

/**
 * Represents the command to pass the current monster's turn. Only requirement is that there must be a competition
 * running at the time, when the command was entered.
 *
 * @author ukgmb
 */
public class CommandPass implements Command<Competition> {

    /**
     * Executes the pass command.
     * @param handle the game to be handled
     * @return result of the execution
     */
    @Override
    public Result execute(Competition handle) {
        handle.action(null, null);
        return Result.success();
    }
}
