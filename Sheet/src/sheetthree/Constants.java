package sheetthree;

/**
 * This class saves all the necessary constants used in the other classes.
 *
 * @author ukgmb
 */
public final class Constants {
    /**
     * Alphabet used for the x-axis of the field.
     */
    public static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    /**
     * Whitespace used for the empty fill of the field.
     */
    public static final char WHITESPACE = ' ';
    /**
     * Marks the corners of each box.
     */
    public static final char PLUS = '+';
    /**
     * Marks the horizontal line fill of a box.
     */
    public static final char HORIZONTAL = '-';
    /**  Marks the vertical line fill of a box. */
    public static final char VERTICAL = '|';
    /**  Game Type is American .*/
    public static final String AMERICAN = "american";
    /**  Game Type is Icelandic. */
    public static final String ICELANDIC = "icelandic";
    /**  Game Type is Swedish. */
    public static final String SWEDISH = "swedish";
    /**  Left line of the box. */
    public static final String LEFT = "left";
    /**  Right line of the box. */
    public static final String RIGHT = "right";
    /**  Up line of the box. */
    public static final String UP = "up";
    /**  Down line of the box. */
    public static final String DOWN = "down";
    /** If you want to quit the game. */
    public static final String QUIT = "quit";
    /**  To mark when player A occupies the box. */
    public static final char PLAYERA = 'A';
    /**  To mark when player B marks the box. */
    public static final char PLAYERB = 'B';

    private Constants() {

    }
}
