package a1.view;

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

    private static final String ERROR_MESSAGE_FILE_NOT_FOUND = "Error, file not found.";


    private final InputStream inputSource;
    private final PrintStream defaultStream;
    private final PrintStream errorStream;

    /**
     * Constructs a new user interface.
     * Provided input source will be closed when finished.
     * @param inputSource Input source used to receive user's input
     * @param defaultStream Default print stream used to print results of application
     * @param errorStream Error Stream used to print error messages
     */
    public UserInteraction(InputStream inputSource, PrintStream defaultStream, PrintStream errorStream) {
        this.inputSource = inputSource;
        this.defaultStream = defaultStream;
        this.errorStream = errorStream;
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
            this.errorStream.println(ERROR_MESSAGE_FILE_NOT_FOUND);
            return false;
        }
        return true;
    }
}
