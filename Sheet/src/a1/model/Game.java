package a1.model;

import a1.view.configurator.Configurator;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

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

    /**
     * Constructs a new game instance.
     */
    public Game() {
        this.allMonsters = new LinkedHashSet<>();
    }

    /**
     * Loads a set of monsters into the game.
     * @param monsters Set of monsters to be loaded
     */
    public void loadMonsters(Set<Monster> monsters) {
        this.allMonsters.addAll(monsters);
    }
}
