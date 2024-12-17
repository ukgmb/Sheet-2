package sheetfour;

/**
 * Represent all the commands which the navigation system accepts from the rover.
 * @author ukgmb
 */
public enum CommandType {
    /** When no command is entered. */
    NO_COMMAND,
    /** Command to move 1 step in a direction. */
    MOVE_STANDARD,
    /** Command to move many step in a direction. */
    MOVE_PARAMETER,
    /** Command to save a new terrain. */
    NEW_TERRAIN,
    /** Command to generate a path for the rover. */
    PATH,
    /** Command to debug the terrain for the developers. */
    DEBUG,
    /** Command to debug the path of the rover for the developers. */
    DEBUG_PATH,
    /** Command to quit the navigation system. */
    QUIT;
}
