package a1.view;

import a1.model.Game;

import java.util.*;

/**
 * Configurations the @code{Game} using a text file.
 *
 * @author ukgmb
 */
public class Configurator {

    private static final String COMMAND_SEPARATOR = " ";
    private static final String UNKNOWN_COMMAND_ERROR = "unknown configuration command: %s";
    private static final String END_COMMAND_PREFIX = "end %s";
    private static final String PREFIX_MESSAGE_LOADED_CONFIGURATION = "Loaded ";
    private static final String DELIMITER_LOADED_CONFIGURATION = ", ";
    private static final String SUFFIX_MESSAGE_LOADED_CONFIGURATION = ".";
    private static final String WHITESPACE = " ";
    private static final String PLURAL_S_SUFFIX = "s";

    private final Game game;
    private final Queue<Command<Configurator>> allDeclarations;
    private final Set<ConfigKeyword> configKeywords = EnumSet.allOf(ConfigKeyword.class);
    private final List<String> allLines;
    private final Map<ConfigKeyword, Integer> count;

    /**
     * Constructs a new configurator.
     *
     * @param game     The game in which the configuration should be loaded
     * @param allLines All the lines of the configuration file
     */
    public Configurator(Game game, List<String> allLines) {
        this.game = game;
        this.allDeclarations = new ArrayDeque<>();
        this.allLines = allLines;
        this.count = new HashMap<>();
    }

    /**
     * Configures the game with the given text lines.
     *
     * @return Success if configuration file was correct. Else returns failure.
     */
    public Result readLines() {

        Iterator<String> iterator = this.allLines.iterator();
        while (iterator.hasNext()) {
            String parsedLine = iterator.next();
            if (parsedLine.isBlank()) {
                continue;
            }
            String[] split = parsedLine.split(COMMAND_SEPARATOR, 2);
            String commandInput = split[0];
            List<String> argumentsInput = new ArrayList<>();
            argumentsInput.add(split[1]);

            ConfigKeyword keyword = retrieveKeyword(commandInput);
            if (keyword == null) {
                return Result.error(UNKNOWN_COMMAND_ERROR.formatted(commandInput));
            }

            if (keyword.requiresMoreLines()) {
                while (iterator.hasNext()) {
                    String line = iterator.next();
                    if (!line.equals(END_COMMAND_PREFIX.formatted(commandInput))) {
                        argumentsInput.add(line);
                        continue;
                    }
                    break;
                }
            }

            ArgumentsCommand arguments = new ArgumentsCommand(argumentsInput);

            try {
                Command<Configurator> command = keyword.provide(arguments);
                this.allDeclarations.add(command);
                this.count.merge(keyword, 1, Integer::sum);
            } catch (InvalidArgumentException e) {
                return Result.error(e.getMessage());
            }

        }
        return Result.success();
    }


    private ConfigKeyword retrieveKeyword(String keyword) {
        for (ConfigKeyword configKeyword : configKeywords) {
            if (configKeyword.matches(keyword)) {
                return configKeyword;
            }
        }
        return null;
    }

    /**
     * Returns for each configuration type the number of times it was declared.
     *
     * @return String containing the count as a message
     */
    public String getDeclarationCount() {
        StringJoiner joiner = new StringJoiner(DELIMITER_LOADED_CONFIGURATION, PREFIX_MESSAGE_LOADED_CONFIGURATION,
                SUFFIX_MESSAGE_LOADED_CONFIGURATION);

        for (ConfigKeyword keyword : this.count.keySet()) {
            joiner.add(this.count.get(keyword).toString() + WHITESPACE + keyword.name().toLowerCase()
                    + (this.count.get(keyword) > 1 ? PLURAL_S_SUFFIX : ""));
        }

        return joiner.toString();

    }

}
