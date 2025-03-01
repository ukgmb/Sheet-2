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

    private static final String ERROR_MESSAGE_COMPETITION_NOT_STARTED = "competition hasn't started yet.";

    /**
     * Executes the pass command.
     * @param handle the game to be handled
     * @return result of the execution
     */
    @Override
    public Result execute(Competition handle) {
        return handle.nextMonstersTurn() ? Result.success() : Result.error(ERROR_MESSAGE_COMPETITION_NOT_STARTED);
    }
}
