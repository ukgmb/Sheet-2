package abschluss.view.commands;

import abschluss.view.Result;
import abschluss.view.UserInteraction;

import java.nio.file.Path;

/**
 * Represents the command to load a new configuration file and restart the game with the new configuration.
 *
 * @author ukgmb
 */
public class CommandLoad implements Command<UserInteraction> {

    private final Path path;

    /**
     * Constructs a new load command.
     * @param path The path of the configuration file to be loaded.
     */
    protected CommandLoad(Path path) {
        this.path = path;
    }

    /**
     * Executes the load command.
     * @param handle the user interaction that handles the game
     * @return Success, if loading succeeded. Else, returns {@code null}
     */
    @Override
    public Result execute(UserInteraction handle) {
        if (handle.handleConfigFile(this.path)) {
            return Result.success();
        }
        return null;
    }
}
