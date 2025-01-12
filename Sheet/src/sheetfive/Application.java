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
        Scanner scanner = new Scanner(System.in);
        Command start = new StartCommand(new HuntForMisterX());
        while (true) {
            String input = scanner.nextLine();
            try {
                execute(input);
            }
            catch (InvalidCommandException exception) {
                System.err.println(exception.getMessage());
            }


        }

    }

    private static void execute(String input) throws InvalidCommandException {
        Command start = new StartCommand(new HuntForMisterX());
        if (input.matches(start.getRegex())) {
            start.execute(input.split(" "));
            return;
        }
        throw start.getException();

    }
}
