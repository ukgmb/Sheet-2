package sheetfive.game;

import java.util.List;
import java.util.LinkedList;

/**
 * Represents a player and his available game tile in {@code HuntForMisterX}.
 *
 * @author ukgmb
 */
public class Player {

    private final String name;
    private List<Tile> resources;

    /**
     * Initializes a new player for {@code HuntForMisterX}.
     * @param name Name of the player
     */
    public Player(String name) {
        this.name = name;
    }
}
