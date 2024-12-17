package sheetfour;

import java.util.Scanner;

/**
 * Class Terrain models the observable terrain of the rover.
 * It is used to check for valid terrains sent by the rover and to navigate the rover through the terrain.
 */
public class Terrain {

    private char[][] visibleTerrain;

    /**
     * Constructor creates a terrain.
     * @param width Width of the terrain.
     * @param height Height of the terrain
     */
    public Terrain(int width, int height) {
        this.visibleTerrain = new char[height][width];
    }

    public void readTerrain() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < this.visibleTerrain.length; i++) {
            String line = scanner.nextLine();
            char[] charLine = line.toCharArray();
            this.visibleTerrain[i] = charLine.clone();
        }
    }

    public void printTerrain() {
        for(int y = 0; y < this.visibleTerrain.length; y++) {
            for(int x = 0; x < this.visibleTerrain[y].length; x++) {
                System.out.print(this.visibleTerrain[y][x]);
            }
            System.out.println();
        }
    }
}
