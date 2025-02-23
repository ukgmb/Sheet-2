package a1.view;

import a1.model.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Class which handles the user's input and application's output.
 *
 * @author ukgmb
 */
public class UserInteraction {

    private static final String ERROR_PREFIX = "Error, %s";
    private static final String ERROR_MESSAGE_FILE_NOT_FOUND = "file not found.";


    private final InputStream inputSource;
    private final PrintStream defaultStream;
    private final PrintStream errorStream;
    private final Game game;
    private boolean isRunning;


    /**
     * Constructs a new user interface.
     * Provided input source will be closed when finished.
     * @param inputSource Input source used to receive user's input
     * @param defaultStream Default print stream used to print results of application
     * @param errorStream Error Stream used to print error messages
     * @param game The current game playing
     */
    public UserInteraction(InputStream inputSource, PrintStream defaultStream, PrintStream errorStream, Game game) {
        this.inputSource = inputSource;
        this.defaultStream = defaultStream;
        this.errorStream = errorStream;
        this.game = game;
        this.isRunning = true;
    }


    /**
     * Handles the configuration file.
     * @param path Path of the configuration file
     * @return {@code true}, if file was found. Else, returns {@code false}
     */
    public boolean handleConfigFile(String path) {
        List<String> allLines;

        try {
            allLines = Files.readAllLines(Path.of(path));
        } catch (IOException e) {
            this.errorStream.printf(ERROR_PREFIX, ERROR_MESSAGE_FILE_NOT_FOUND);
            return false;
        }

        final Configurator configurator = new Configurator(this.game);
        handleResult(configurator.configure(allLines));
        return true;
    }

    /**
     * Starts the interaction with the user.
     * Continues until no more input is provided and then closes the corresponding input stream.ffidcbakbdkabdfkbfhkbeshkbfhksbrfhkbshkfbhks
     */
    public void handleUserInput() {

    }

    private void handleResult(Result result) {
        if (result == null) {
            return;
        }

        PrintStream outputStream = switch (result.getType()) {
            case SUCCESS -> this.defaultStream;
            case FAILURE -> this.errorStream;
        };
        outputStream.println((result.getType() == ResultType.FAILURE ? ERROR_PREFIX : "")
                + (result.getMessage() == null ? "" : result.getMessage()));
    }
}
