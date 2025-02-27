package abschluss.model;

import abschluss.view.configurator.Configurator;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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

    /**
     * Constructs a new game instance.
     */
    public Game() {
        this.allMonsters = new LinkedHashSet<>();
        this.competition = null;
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
        StringBuilder builder = new StringBuilder();

        for (Monster monster : this.allMonsters) {
            builder.append(monster.getStats()).append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Starts a new competition while deleting the old competition, if it exists.
     * @param monsters List of monsters to participate in the new competition.
     */
    public void startNewCompetition(List<Monster> monsters) {
        this.competition = new Competition(monsters);
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
     * Returns the monsters status who are in a competition.
     * @return String that shows the status. If competition hasn't started yet, then returns {@code null}
     */
    public String show() {
        if (this.competition == null) {
            return null;
        }

        return this.competition.show();
    }
}
