package sheetfive.game;

/**
 * Represents a game of hunt for mister x.
 *
 * @author ukgmb
 */
public class HuntForMisterX {

    private boolean isRunning;

    /**
     * Creates a new game of Hunt for Mister X.
     */
    public HuntForMisterX() {
        this.isRunning = true;
    }

    /**
     * Returns whether the game is still running or not.
     * @return The running status of the game
     */
    public boolean gameIsRunning() {
        return this.isRunning;
    }

    /**
     * Quites the game.
     */
    public void quit() {
        this.isRunning = false;
    }
}
