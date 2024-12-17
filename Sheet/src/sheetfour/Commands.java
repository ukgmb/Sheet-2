package sheetfour;

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

    private static final String TERRAIN_READER_REGEX = "^new \\d+ \\d+$";

    private static final String PATH = "path";
    private static final String DEBUG = "debug";
    private static final String DEBUG_PATH = "debug-path";

    private static final String QUIT = "quit";

    private static final int MINIMUM_INPUT_LENGTH = 2;

    private String newInput;
    private CommandType commandType; // 1-2:up,down,left,right 3:new 4:path,debug,debug-path 5:quit

    /**
     * Constructor initializes the attributes with values that represent no command.
     */
    public Commands() {
        this.commandType = CommandType.NO_COMMAND;
        this.newInput = "";
    }

    /**
     * This method checks for syntactical correctness of a command from the rover.
     *
     * @param newInput The input which will be checked for syntactical correctness.
     * @return True, if syntax of the command is correct. Else, will return false.
     */
    public boolean isNewInputSyntaxCorrect(String newInput) {
        this.newInput = newInput;
        if (this.newInput.length() < MINIMUM_INPUT_LENGTH) {
            return false;
        }
        switch (this.newInput) {
            case UP:
                this.commandType = CommandType.MOVE_STANDARD;
                return true;
            case DOWN:
                this.commandType = CommandType.MOVE_STANDARD;
                return true;
            case LEFT:
                this.commandType = CommandType.MOVE_STANDARD;
                return true;
            case RIGHT:
                this.commandType = CommandType.MOVE_STANDARD;
                return true;
            case PATH:
                this.commandType = CommandType.PATH;
                return true;
            case DEBUG:
                this.commandType = CommandType.DEBUG;
                return true;
            case DEBUG_PATH:
                this.commandType = CommandType.DEBUG_PATH;
                return true;
            case QUIT:
                this.commandType = CommandType.QUIT;
                return true;
            default:
        }
        if (this.newInput.matches(DIRECTION_REGEX)) {
            this.commandType = CommandType.MOVE_PARAMETER;
            return true;
        }
        if (this.newInput.matches(TERRAIN_READER_REGEX)) {
            this.commandType = CommandType.NEW_TERRAIN;
            return true;
        }
        return false;
    }

    /**
     * Returns the type of command.
     * @return The type of command.
     */
    public CommandType getCommandType() {
        return this.commandType;
    }

}
