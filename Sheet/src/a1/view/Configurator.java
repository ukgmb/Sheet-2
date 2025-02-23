package a1.view;

import a1.model.Game;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Configurations the @code{Game} using a text file.
 *
 * @author ukgmb
 */
public class Configurator {

    private static final String COMMAND_SEPARATOR = " ";
    private static final String UNKNOWN_COMMAND_ERROR = "unknown command: %s";

    private Game game;
    private Queue<Command<Configurator>> allDeclarations;
    private Set<ConfigKeywords> configKeywords = EnumSet.allOf(ConfigKeywords.class);
    private int numberOfActions;
    private int numberOfMonsters;

    /**
     * Constructs a new configurator.
     * @param game The game in which the configuration should be loaded
     */
    public Configurator(Game game) {
        this.game = game;
        this.numberOfActions = 0; //Before configuration 0 actions and 0 monsters are loaded.
        this.numberOfMonsters = 0;
    }

    /**
     * Configures the game with the given text lines.
     * @param allLines Lines of text from the configuration file
     * @return Success if configuration file was correct. Else returns failure.
     */
    public Result configure(List<String> allLines) {

        Iterator<String> iterator = allLines.iterator();
        while (iterator.hasNext()) {
            String[] split = iterator.next().split(COMMAND_SEPARATOR, -1);
            String keyword = split[0];
            String[] arguments = Arrays.copyOfRange(split, 1, split.length);

            ConfigKeywords command = retrieveKeyword(keyword);
            if (command == null) {
                return Result.error(UNKNOWN_COMMAND_ERROR.formatted(keyword));
            }
        }
        return Result.success();
    }

    private ConfigKeywords retrieveKeyword(String keyword) {
        for (ConfigKeywords configKeyword: configKeywords) {
            if (configKeyword.matches(keyword)) {
                return configKeyword;
            }
        }
        return null;
    }


}
