package abschluss;

import abschluss.model.Game;
import abschluss.view.UserInteraction;

import java.nio.file.Path;

/**
 * The class offering the entry point for the application.
 *
 * @author ukgmb
 */
public final class Application {

    private static final String ERROR_MESSAGE_COMMAND_LINE_ARGUMENTS = "Error, command line arguments expected.";

    private Application() {
        //utility class
    }

    /**
     * The entry point for the application. Command line arguments are expected.
     * @param args path of text file
     */
    public static void main(String[] args) {
        if (args == null) {
            System.err.println(ERROR_MESSAGE_COMMAND_LINE_ARGUMENTS);
            return;
        }


        Game game = new Game();
        UserInteraction userInteraction = new UserInteraction(System.in, System.out, System.err, game);
        if (userInteraction.handleConfigFile(Path.of(args[0]))) {
            userInteraction.handleUserInput();
        }






    }

}