package sheetfour;

import java.util.Scanner;

/**
 * Class Terrain models the observable terrain of the rover.
 * It is used to check for valid terrains sent by the rover and to navigate the rover through the terrain.
 *
 * @author ukgmb
 */
public class Terrain {

    private static final char PASSABLE_TERRAIN = ' ';
    private static final char ROCK_RIGHT = '/';
    private static final char ROCK_LEFT = '\\';
    private static final char ROCK_HORIZONTAL = '_';
    private static final char ROCK_VERTICAL = '|';
    private static final char ROCK_SINGLE = '*';
    private static final char ROVER = 'R';
    private static final char DESTINATION = 'x';
    private static final char ROVER_IS_DESTINATION = 'X';
    private static final String SYNTAX = " /\\_|*RxX";
    private static final String ALL_ROCKS = "/\\_|";
    private static final int MAX_NUM_R_X = 1;


    private final char[][] visibleTerrain;
    private final int width;
    private final int height;

    /**
     * Constructor creates a terrain.
     *
     * @param width  Width of the terrain.
     * @param height Height of the terrain
     */
    public Terrain(int width, int height) {
        this.visibleTerrain = new char[height][width];
        this.width = width;
        this.height = height;
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
    public boolean checkForValidTerrain() {
        if (this.visibleTerrain.length != this.height) {
            return false;
        }
        for (char[] y : this.visibleTerrain) {
            if (y.length != this.width) {
                return false;
            }
        }
        int count_R = 0;
        int count_x = 0;
        int count_X = 0;
        for (int y = 0; y < this.visibleTerrain.length; y++) {
            for (int x = 0; x < this.visibleTerrain[y].length; x++) {
                if (SYNTAX.indexOf(this.visibleTerrain[y][x]) == -1) {
                    return false;
                }
                switch (this.visibleTerrain[y][x]) {
                    case ROVER:
                        count_R++;
                        break;
                    case DESTINATION:
                        count_x++;
                        break;
                    case ROVER_IS_DESTINATION:
                        count_X++;
                        break;
                    default:
                }
            }
        }
        if (!((count_R == MAX_NUM_R_X && count_x == count_R) || (count_X == MAX_NUM_R_X && count_x + count_R == 0))) {
            return false;
        }


        return true;

    }

    public boolean checkForValidRocks() {
        for (int y = 0; y < this.visibleTerrain.length; y++) {
            for (int x = 0; x < this.visibleTerrain[y].length; x++) {
                if (ALL_ROCKS.indexOf(this.visibleTerrain[y][x]) != -1) {
                    int o;
                }
            }
        }

        return true;
    }
}
