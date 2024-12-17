package sheetfour;

import java.util.Scanner;

/**
 * Class Terrain models the observable terrain of the rover.
 * It is used to check for valid terrains sent by the rover and to navigate the rover through the terrain.
 * @author ukgmb
 */
public class Terrain {

    private char[][] visibleTerrain;

    /**
     * Constructor creates a terrain.
     *
     * @param width  Width of the terrain.
     * @param height Height of the terrain
     */
    public Terrain(int width, int height) {
        this.visibleTerrain = new char[height][width];
    }

    /**
     * Reads the measured terrain by the rover in the terminal.
     */
    public void readTerrain() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.visibleTerrain.length; i++) {
            String line = scanner.nextLine();
            char[] charLine = line.toCharArray();
            this.visibleTerrain[i] = charLine.clone();
        }
    }

    /**
     * Prints the saved terrain.
     */
    public void printTerrain() {
        for (int y = 0; y < this.visibleTerrain.length; y++) {
            for (int x = 0; x < this.visibleTerrain[y].length; x++) {
                System.out.print(this.visibleTerrain[y][x]);
            }
            System.out.println();
        }
    }

    /**
     * Checks the terrain for validity.
     */
    public void checkForValidTerrain() {

    }
}
