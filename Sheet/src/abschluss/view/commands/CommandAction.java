package abschluss.view.commands;

import abschluss.model.Action;
import abschluss.model.Competition;
import abschluss.model.Monster;
import abschluss.view.Result;

/**
 * This class represent the action command. It requires the action to be played and the target monster.
 *
 * @author ukgmb
 */
public class CommandAction implements Command<Competition> {

    private final Action action;
    private final Monster target;

    /**
     * Constructs a new action command.
     * @param action Action to be played
     * @param target Monster to be targeted by the action
     */
    public CommandAction(Action action, Monster target) {
        this.action = action;
        this.target = target;
    }

    /**
     * Executes the action command.
     * @param handle the game to be handled
     * @return result of the execution
     */
    @Override
    public Result execute(Competition handle) {
        return Result.success("Yeah");
    }
}
