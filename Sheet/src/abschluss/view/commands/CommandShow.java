package abschluss.view.commands;

import abschluss.model.Game;
import abschluss.view.Result;

/**
 * This class represents the show command which can show some things from the game, depending on the given argument.
 *
 * @author ukgmb
 */
public class CommandShow implements Command<Game> {

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
    public Result execute(Game handle) {
        if (this.parameter == null) {
            String resultShow = handle.show();
            return resultShow == null ? Result.error(ERROR_MESSAGE_COMPETITION_NOT_STARTED)
                    : Result.success(resultShow);
        }

        return switch (this.parameter) {
            case ACTIONS -> {
                String resultActions = handle.showActions();
                yield resultActions == null ? Result.error(ERROR_MESSAGE_COMPETITION_NOT_STARTED)
                        : Result.success(resultActions);
            }
            case MONSTERS -> Result.success(handle.showMonsters());
            case STATS -> {
                String resultStats = handle.showStats();
                yield resultStats == null ? Result.error(ERROR_MESSAGE_COMPETITION_NOT_STARTED)
                        : Result.success(resultStats);
            }
        };

    }
}
