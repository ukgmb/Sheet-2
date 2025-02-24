package a1;

import a1.model.Element;
import a1.model.Game;
import a1.model.StatusCondition;
import a1.model.effects.StrengthType;
import a1.model.effects.TargetMonster;
import a1.view.ArgumentsEffect;
import a1.view.InvalidArgumentException;
import a1.view.UserInteraction;
import com.sun.jdi.connect.Connector;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        userInteraction.handleConfigFile(Path.of(args[0]));
        userInteraction.handleUserInput();






    }

}