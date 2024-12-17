package sheetfour;

import java.util.Scanner;

/**
 * This class represents the navigation system of the rover.
 * It navigates the rover through the terrain and is useful for finding a path to its destination.
 * @author ukgmb
 */
public class NavigationSystem {

    public Terrain terrain;

    /**
     * Starts and runs the rover's navigation system.
     * It receives commands from the rover and executes them.
     */
    public void runNavigationSystem() {
        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();

        String input;
        do {
            input = scanner.nextLine();
        }
        while (!commands.isNewInputSyntaxCorrect(input));

        if (commands.getCommandType().equals(CommandType.NEW_TERRAIN)) {
            this.terrain = new Terrain(4, 4);
            this.terrain.readTerrain();
        }


    }
}
