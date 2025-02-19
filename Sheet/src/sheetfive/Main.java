package sheetfive;

import sheetfive.game.HuntForMisterX;

/**
 * This application runs the game.
 *
 * @author ukgmb
 */
public final class Main {

    private Main() {

    }

    /**
     * The main entry point for the application.
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        HuntForMisterX game = new HuntForMisterX();
        UserInteraction ui = new UserInteraction(game);
        ui.start();


    }

}
