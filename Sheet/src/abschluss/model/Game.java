package abschluss.model;

import abschluss.view.UserInteraction;
import abschluss.view.configurator.Configurator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

/**
 * This class handles all the game mechanics. It organizes the monsters and its actions during competitions.
 *
 * <p> Before starting a game, its actions and monsters must be declared using the {@link Configurator} by
 * uploading the path of the configuration text file.</p>
 *
 * @author ukgmb
 */
public class Game {

    private final Set<Monster> allMonsters;
    private Competition competition;
    private final UserInteraction handler;
    private final RandomGenerator randomGenerator;

    /**
     * Constructs a new game instance.
     * @param handler The user interaction that handles this game
     * @param randomGenerator The random generator used to generate random numbers
     */
    public Game(UserInteraction handler, RandomGenerator randomGenerator) {
        this.allMonsters = new LinkedHashSet<>();
        this.competition = null;
        this.handler = handler;
        this.randomGenerator = randomGenerator;
    }

    /**
     * Loads a set of monsters into the game.
     * @param monsters Set of monsters to be loaded
     */
    public void loadMonsters(Set<Monster> monsters) {
        this.allMonsters.addAll(monsters);
    }

    /**
     * Returns all the monsters with its stats as a string.
     * @return All monsters with its stats.
     */
    public String showMonsters() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        for (Monster monster : this.allMonsters) {
            joiner.add(monster.getStats());
        }

        return joiner.toString();
    }

    /**
     * Starts a new competition while deleting the old competition, if it exists.
     * @param monsters List of monsters to participate in the new competition.
     */
    public void startNewCompetition(List<Monster> monsters) {
        this.competition = new Competition(monsters, this.handler, this.randomGenerator);
    }

    /**
     * Parses the monster to which the name matches.
     * @param name Name of the monster to be parsed
     * @return monster, if found. Else, returns {@code null}
     */
    public Monster getMonster(String name) {
        for (Monster monster : this.allMonsters) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }
        return null;
    }

    /**
     * Gets the message of the game to be give to the user.
     * @return The message, if there is one. Else, returns {@code null}
     */
    public String getMessage() {
        if (this.competition == null) {
            return null;
        }
        return this.competition.whatMonsterShouldDo();
    }

    /**
     * Returns the action, from the current monster in competition.
     * @param actionName Name of the action
     * @return Corresponding action, if found. Else, returns {@code null}
     */
    public Action getAction(String actionName) {
        return this.competition.getAction(actionName);
    }

    /**
     * Returns the current competition.
     * @return The current competition
     */
    public Competition getCompetition() {
        return this.competition;
    }
}
