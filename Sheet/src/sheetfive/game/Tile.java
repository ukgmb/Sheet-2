package sheetfive.game;

/**
 * Represents a game tile in {@code HuntForMisterX}.
 *
 * @author ukgmb
 */
public abstract class Tile {

    protected final String name;
    protected final int id;

    /**
     * Constructs a new game tile.
     * @param name Name of the game tile
     * @param id Identification number of the game tile
     */
    public Tile(String name, int id) {
        this.name = name;
        this.id = id;
    }

}
