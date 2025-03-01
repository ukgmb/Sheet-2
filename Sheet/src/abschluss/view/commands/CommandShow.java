package abschluss.view.commands;

import abschluss.model.Competition;
import abschluss.view.Result;

/**
 * This class represents the show command which can show some things during the competition, depending on the given argument.
 *
 * @author ukgmb
 */
public class CommandShow implements Command<Competition> {

    private static final String ERROR_MESSAGE_COMPETITION_NOT_STARTED = "competition hasn't started yet.";

    private final ShowParameter parameter;

    /**
     * Constructs a new show command with its parameter.
     * @param parameter Parameter describes what to show(see {@link ShowParameter})
     */
    protected CommandShow(ShowParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public Result execute(Competition handle) {
        if (this.parameter == null) {
            return Result.success(handle.show());
        }

        return switch (this.parameter) {
            case ACTIONS -> Result.success(handle.showActions());
            case STATS -> Result.success(handle.showStats());
        };

    }
}
