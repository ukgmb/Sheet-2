package sheetthree;

/**
 * Class creates and runs the game.
 *
 * @author ukgmb
 */
public final class DotsAndBox {

    private DotsAndBox() {

    }

    /**
     * Main method creates and runs the game.
     *
     * @param args The arguments for the field length and pre-Layout.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.runGame(args);
    }
}
