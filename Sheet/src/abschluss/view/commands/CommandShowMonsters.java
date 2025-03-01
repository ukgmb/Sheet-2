package abschluss.view.commands;

import abschluss.model.Game;
import abschluss.view.Result;

/**
 * This class represents the show command which can show all declared monsters from the game,
 * depending on the given argument.
 *
 * @author ukgmb
 */
public class CommandShowMonsters implements Command<Game> {

    @Override
    public Result execute(Game handle) {
        return Result.success(handle.showMonsters());
    }
}
