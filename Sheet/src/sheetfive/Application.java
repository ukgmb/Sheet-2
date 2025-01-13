package sheetfive;

import sheetfive.commands.Command;
import sheetfive.commands.StartCommand;
import sheetfive.exceptions.InvalidCommandException;
import sheetfive.exceptions.InvalidCommandSyntaxException;

import java.util.Scanner;

/**
 * This application runs the game.
 *
 * @author ukgmb
 */
public final class Application {

    private Application() {

    }

    /**
     * The main entry point for the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        HuntForMisterX game = new HuntForMisterX();
        UserInteraction ui = new UserInteraction(game);
        ui.start();


    }

}
