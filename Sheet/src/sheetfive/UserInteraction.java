package sheetfive;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sheetfive.commands.Command;
import sheetfive.commands.PlaceCommand;
import sheetfive.commands.QuitCommand;
import sheetfive.commands.StartCommand;
import sheetfive.exceptions.InvalidCommandSyntaxException;
import sheetfive.game.HuntForMisterX;

/**
 * This class handles the user interaction.
 *
 * @author ukgmb
 */
public class UserInteraction {

    private static final String SEPERATOR = " ";
    private static final String COMMAND_EXECUTION_SUCCESS = "OK";

    private List<Command> allCommands;
    private HuntForMisterX game;

    /**
     * Constructs a new UI for the player to play the game.
     * @param game The current game to be played
     */
    public UserInteraction(HuntForMisterX game) {
        this.game = game;
        initializeCommands();

    }

    /**
     * Starts the user interaction.
     */
    public void start() {

        try (Scanner scanner = new Scanner(System.in)) {
            while (this.game.gameIsRunning()) {
                try {
                    String input = scanner.nextLine();
                    executeCommand(input);
                    if (this.game.gameIsRunning()) {
                        System.out.println(COMMAND_EXECUTION_SUCCESS);
                    }
                } catch (InvalidCommandSyntaxException exception) {
                    System.err.println(exception.getMessage());
                }
            }
        }

    }

    private void executeCommand(String input) throws InvalidCommandSyntaxException {
        for (Command command: this.allCommands) {
            if (command.inputMatchesCommand(input)) {
                command.execute(input.split(SEPERATOR));
                return;
            }
        }

        throw new InvalidCommandSyntaxException();
    }


    private void initializeCommands() {
        this.allCommands = new ArrayList<>();
        this.allCommands.add(new StartCommand(this.game));
        this.allCommands.add(new PlaceCommand(this.game));
        this.allCommands.add(new QuitCommand(this.game));
    }
}
