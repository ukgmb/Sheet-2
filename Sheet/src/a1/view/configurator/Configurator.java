package a1.view.configurator;

import a1.model.Action;
import a1.model.Game;
import a1.model.Monster;
import a1.view.*;

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
    private static final String SAME_ACTION_NAME_ERROR = "all actions must have different names. '%s' was used"
            + " multiple times.";
    private static final String SAME_MONSTER_NAME_ERROR = "all monsters must have different names. '%s' was used"
            + " multiple times.";
    private static final String WRONG_ORDER_ERROR = "actions have to be declared first, then monsters.";
    private final Game game;
    private final List<Action> allDeclaredActions;
    private final Set<Monster> allDeclaredMonsters;
    private final List<String> allLines;
    private final Map<ConfigKeyword, Integer> count;
    private boolean allActionsDeclared;

    /**
     * Constructs a new configurator.
     *
     * @param game     The game in which the configuration should be loaded
     * @param allLines All the lines of the configuration file
     */
    public Configurator(Game game, List<String> allLines) {
        this.game = game;
        this.allDeclaredActions = new ArrayList<>();
        this.allDeclaredMonsters = new HashSet<>();
        this.allLines = allLines;
        this.count = new HashMap<>();
        this.allActionsDeclared = false;
    }

    /**
     * Configures the game with the given text lines.
     *
     * @return Success if configuration file was correct. Else returns failure.
     */
    public Result readLines() {

        Iterator<String> iterator = this.allLines.iterator();
        while (iterator.hasNext()) {
            String parsedLine = iterator.next().trim();
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

            Result handling = handleCommand(argumentsInput, keyword);
            if (handling.getType() == ResultType.FAILURE) {
                return handling;
            }

        }
        return Result.success();
    }

    private Result handleCommand(List<String> argumentsInput, ConfigKeyword keyword) {
        ArgumentsConfiguration arguments = new ArgumentsConfiguration(argumentsInput, this.allDeclaredActions);

        try {
            if (keyword == ConfigKeyword.ACTION) {
                if (this.allActionsDeclared) {
                    return Result.error(WRONG_ORDER_ERROR);
                }
                Action action = ProviderAction.ACTION.provide(arguments);
                if (sameNameAction(action)) {
                    return Result.error(SAME_ACTION_NAME_ERROR.formatted(action.getName()));
                }
                this.allDeclaredActions.add(action);

            } else {
                Monster monster = ProviderMonster.MONSTER.provide(arguments);
                if (sameNameMonster(monster)) {
                    return Result.error(SAME_MONSTER_NAME_ERROR.formatted(monster.getName()));
                }
                this.allDeclaredMonsters.add(monster);
                this.allActionsDeclared = true;
            }
            this.count.merge(keyword, 1, Integer::sum);
        } catch (InvalidArgumentException e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    private boolean sameNameAction(Action input) {
        for (Action action : this.allDeclaredActions) {
            if (action.getName().equals(input.getName())) {
                return true;
            }
        }
        return false;
    }

    private boolean sameNameMonster(Monster input) {
        for (Monster monster : this.allDeclaredMonsters) {
            if (monster.getName().equals(input.getName())) {
                return true;
            }
        }
        return false;
    }


    private ConfigKeyword retrieveKeyword(String input) {
        for (ConfigKeyword keyword : ConfigKeyword.values()) {
            if (keyword.matches(input)) {
                return keyword;
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

    /**
     * Loads the configuration into the game.
     */
    public void loadConfiguration() {
        this.game.loadMonsters(this.allDeclaredMonsters);
    }

}
