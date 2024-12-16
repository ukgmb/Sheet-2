package sheetfour;

import java.util.Scanner;

/**
 * Class Commands receives and checks the commands of the rover.
 *
 * @author ukgmb
 */
public class Commands {

    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String DIRECTION_REGEX = "^(up|down|left|right) \\d+";

    private static final String TERRAIN_READER = "^new \\d+ \\d+$";

    private static final String PATH = "path";
    private static final String DEBUG = "debug";
    private static final String DEBUG_PATH = "debug-path";

    private static final int MINIMUM_INPUT_LENGTH = 2;

    private String newInput;
    private String correctInput;
    private int inputType; // 1-2:up,down,left,right 3:new 4:path,debug,debug-path

    public void scanForInput() {
        Scanner scanner = new Scanner(System.in);
        this.newInput = scanner.nextLine().trim();
    }

    public boolean isNewInputSyntaxCorrect() {
        if (this.newInput.length() < MINIMUM_INPUT_LENGTH) {
            return false;
        }
        switch (this.newInput) {
            case UP:
                this.inputType = 1;
                return true;
            case DOWN:
                this.inputType = 1;
                return true;
            case LEFT:
                this.inputType = 1;
                return true;
            case RIGHT:
                this.inputType = 1;
                return true;
            case PATH:
                this.inputType = 4;
                return true;
            case DEBUG:
                this.inputType = 4;
                return true;
            case DEBUG_PATH:
                this.inputType = 4;
                return true;
            default:
        }
        if (this.newInput.matches(DIRECTION_REGEX)) {
            this.inputType = 2;
            return true;
        }
        if (this.newInput.matches(TERRAIN_READER)) {
            this.inputType = 3;
            return true;
        }
        return false;
    }

    public int getInputType() {
        return this.inputType;
    }

}
