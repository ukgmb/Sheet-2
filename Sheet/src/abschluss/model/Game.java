package abschluss.model;

import abschluss.view.UserInteraction;
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
    private final UserInteraction handler;

    /**
     * Constructs a new game instance.
     * @param handler The user interaction that handles this game
     */
    public Game(UserInteraction handler) {
        this.allMonsters = new LinkedHashSet<>();
        this.competition = null;
        this.handler = handler;
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
        this.competition = new Competition(monsters, this.handler);
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

    /**
     * Returns the current monster's in competition list of possible actions.
     * @return String containing all the actions, if competition started. Else, returns {@code null}
     */
    public String showActions() {
        if (this.competition == null) {
            return null;
        }

        return this.competition.showActions();
    }

    /**
     * Shows the stats of the current monster in the competition.
     * @return String containing all the stats, if competition started. Else, returns {@code null}
     */
    public String showStats() {
        if (this.competition == null) {
            return null;
        }

        return this.competition.showStats();
    }

    /**
     * Passes the current monster's turn in a competition.
     * @return {@code true}, if successful. {@code false}, if competition hasn't started yet
     */
    public boolean pass() {
        return this.competition != null && this.competition.nextMonstersTurn();
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
}
