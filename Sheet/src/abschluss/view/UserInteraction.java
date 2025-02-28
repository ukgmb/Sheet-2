package abschluss.view;

import abschluss.model.Game;
import abschluss.model.RandomGenerator;
import abschluss.view.commands.ArgumentsCommand;
import abschluss.view.commands.KeywordGame;
import abschluss.view.commands.KeywordUserInteraction;
import abschluss.view.configurator.Configurator;
import abschluss.view.commands.Command;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Class which handles the user's input and application's output.
 *
 * @author ukgmb
 */
public class UserInteraction {

    private static final String ERROR_PREFIX = "Error, ";
    private static final String ERROR_MESSAGE_FILE_NOT_FOUND = "file not found.";
    private static final String ERROR_UNKNOWN_COMMAND_FORMAT = ERROR_PREFIX + "unknown command: %s";
    private static final String ERROR_TOO_MANY_ARGUMENTS = ERROR_PREFIX + "too many arguments provided.";
    private static final String COMMAND_SEPARATOR = " ";


    private final Set<KeywordUserInteraction> keywordsUI = EnumSet.allOf(KeywordUserInteraction.class);
    private final Set<KeywordGame> keywordsGame = EnumSet.allOf(KeywordGame.class);
    private final PrintStream defaultStream;
    private final PrintStream errorStream;
    private Game game;
    private boolean isRunning;

    private final Scanner scanner;
    private final RandomGenerator random;


    /**
     * Constructs a new user interface.
     * Provided input source will be closed when finished.
     *
     * @param inputSource   Input source used to receive user's input
     * @param defaultStream Default print stream used to print results of application
     * @param errorStream   Error Stream used to print error messages
     */
    public UserInteraction(InputStream inputSource, PrintStream defaultStream, PrintStream errorStream) {
        this.defaultStream = defaultStream;
        this.errorStream = errorStream;
        this.isRunning = true;
        this.scanner = new Scanner(inputSource);
        this.random = new RandomGenerator(new Random());
    }


    /**
     * Handles the configuration file.
     *
     * @param path Path of the configuration file
     * @return {@code true}, if parsing config file successes. Else, returns {@code false}
     */
    public boolean handleConfigFile(Path path) {
        List<String> allLines;

        try {
            allLines = Files.readAllLines(path);
        } catch (IOException e) {
            this.errorStream.println(ERROR_PREFIX + ERROR_MESSAGE_FILE_NOT_FOUND);
            return false;
        }

        final Configurator configurator = new Configurator(allLines);

        Result result = configurator.readLines();
        if (result.getType() == ResultType.FAILURE) {
            this.isRunning = false;
            this.errorStream.println(ERROR_PREFIX + result.getMessage());
            this.errorStream.println();

            return false;
        } else {
            printList(allLines);
            this.defaultStream.println();
            this.defaultStream.println(configurator.getDeclarationCount());
            this.defaultStream.println();
            this.game = new Game(this);
            configurator.loadConfiguration(this.game);
            return true;
        }
    }

    /**
     * Starts the interaction with the user.
     * Continues until no more input is provided and then closes the corresponding input stream.
     */
    public void handleUserInput() {
        this.isRunning = true;
        while (this.isRunning) {
            String instructions = this.game.getMessage();
            if (instructions != null) {
                this.defaultStream.println(instructions);
            }
            if (this.scanner.hasNextLine()) {
                handleLine(this.scanner.nextLine());
            }
        }

    }

    private void handleLine(String line) {
        String[] split = line.split(COMMAND_SEPARATOR, 2);
        String command = split[0];
        String arguments = null;
        if (split.length > 1) {
            arguments = split[1];
        }

        if (!findAndHandleCommand(this.keywordsUI, this, command, arguments)
                && !findAndHandleCommand(this.keywordsGame, this.game, command, arguments)) {
            this.errorStream.printf((ERROR_UNKNOWN_COMMAND_FORMAT) + "%n", command);
        }
    }

    private <S, T extends Keyword<Command<S>, ArgumentsCommand>> boolean
        findAndHandleCommand(Set<T> keywords, S handle, String command, String arguments) {
        T keyword = retrieveKeyword(keywords, command);
        if (keyword != null) {
            handleCommand(handle, arguments, keyword);
            return true;
        }
        return false;
    }

    private static <T extends Keyword<?, ?>> T retrieveKeyword(Collection<T> keywords, String command) {
        for (T keyword : keywords) {
            if (keyword.matches(command)) {
                return keyword;
            }
        }
        return null;
    }

    private <S, T extends Keyword<Command<S>, ArgumentsCommand>> void handleCommand(S handle, String arguments, T keyword) {

        ArgumentsCommand argumentsHolder = new ArgumentsCommand(arguments, this.game);
        Command<S> providedCommand;
        try {
            providedCommand = keyword.provide(argumentsHolder);
        } catch (InvalidArgumentException e) {
            this.errorStream.println(ERROR_PREFIX + e.getMessage());
            return;
        }

        if (!argumentsHolder.isExhausted()) {
            this.errorStream.println(ERROR_TOO_MANY_ARGUMENTS);
            return;
        }

        handleResult(providedCommand.execute(handle));
    }

    private void handleResult(Result result) {
        if (result == null) {
            return;
        }

        PrintStream outputStream = switch (result.getType()) {
            case SUCCESS -> this.defaultStream;
            case FAILURE -> this.errorStream;
        };
        if (result.getMessage() != null) {
            outputStream.println((result.getType() == ResultType.FAILURE ? ERROR_PREFIX + (result.getMessage())
                    : result.getMessage()));
            outputStream.println();
        }

    }

    private <T> void printList(List<T> list) {
        for (T entry : list) {
            this.defaultStream.println(entry);
        }
    }

    /**
     * Stops the user's interaction and the game is quited.
     */
    public void quit() {
        this.isRunning = false;
        this.scanner.close();
    }


}
