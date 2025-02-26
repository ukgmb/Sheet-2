package a1.view.commands;

import a1.model.Game;
import a1.view.Result;

/**
 * This class represents the show command which can show some things from the game, depending on the given argument.
 *
 * @author ukgmb
 */
public class CommandShow implements Command<Game> {

    private final ShowParameter parameter;

    /**
     * Constructs a new show command with its parameter.
     * @param parameter Parameter describes what to show(see {@link ShowParameter})
     */
    public CommandShow(ShowParameter parameter) {
        this.parameter = parameter;
    }

    @Override
    public Result execute(Game handle) {
        if (this.parameter == ShowParameter.MONSTERS) {
            return Result.success(handle.showMonsters());
        }
        return Result.error("Not declared yet");
    }
}
