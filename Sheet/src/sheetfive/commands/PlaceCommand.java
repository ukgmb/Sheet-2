package sheetfive.commands;

import sheetfive.game.HuntForMisterX;
import sheetfive.exceptions.WrongPlaceException;

/**
 * Represents the command to place a tile.
 *
 * @author ukgmb
 */
public class PlaceCommand extends Command {

    private static final String PATTERN = "^place\\s[A,E,I,S,X][1-3][R,V]\\s[0-6]\\s[A,E,I,S,X][1-3][R,V]$";

    /**
     * Constructs a new place command.
     * @param game The current game playing
     */
    public PlaceCommand(HuntForMisterX game) {
        super(new WrongPlaceException(), PATTERN, game);
    }

    /**
     * Executes the place command.
     * @param arguements Command arguments
     */
    public void execute(String[] arguements) {
        System.out.println("Place command successful.");
    }
}
