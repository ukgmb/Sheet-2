package sheetthree;

/**
 * This class models a single box in the field.
 * It saves which lines have been filled and which Player it currently occupies.
 *
 * @author ukgmb
 */
public class Box {

    /**
     * Datatype which represents which Player currently occupies the box.
     */
    enum FilledByPlayer {
        PLAYER1,
        PLAYER2,
        NOPLAYER;
    }

    private boolean upLine;
    private boolean downLine;
    private boolean leftLine;
    private boolean rightLine;
    private FilledByPlayer filledByPlayer;

    /**
     * This constructor initializes the class attributes.
     * By default, a Box has no lines filled and no Player occupies the box.
     */
    public Box() {
        this.upLine = false;
        this.downLine = false;
        this.leftLine = false;
        this.rightLine = false;
        this.filledByPlayer = FilledByPlayer.NOPLAYER;
    }

    /**
     * Upper Line of the box will be filled.
     */
    public void addUpLine() {
        this.upLine = true;
    }

    /**
     * Lower Line of the box will be filled.
     */
    public void addDownLine() {
        this.downLine = true;
    }

    /**
     * Left Line of the box will be filled.
     */
    public void addLeftLine() {
        this.leftLine = true;
    }

    /**
     * Right Line of the box will be filled.
     */
    public void addRightLine() {
        this.rightLine = true;
    }

    /**
     * Returns the status of the Up Line.
     *
     * @return Will return true, if Up Line is filled. Will return false, if Up Line is not filled.
     */
    public boolean getUpLine() {
        return this.upLine;
    }

    /**
     * Returns the status of the Down Line.
     *
     * @return Will return true, if Down Line is filled. Will return false, if Down Line is not filled.
     */
    public boolean getDownLine() {
        return this.downLine;
    }


    /**
     * Returns the status of the Left Line.
     *
     * @return Will return true, if Left Line is filled. Will return false, if Left Line is not filled.
     */
    public boolean getLeftLine() {
        return this.leftLine;
    }

    /**
     * Returns the status of the Right Line.
     *
     * @return Will return true, if Right Line is filled. Will return false, if Right Line is not filled.
     */
    public boolean getRightLine() {
        return this.rightLine;
    }

    /**
     * Returns the status of the Box occupation.
     *
     * @return The Player who occupies the box.
     */
    public FilledByPlayer getFilledByPlayer() {
        return this.filledByPlayer;
    }

    /**
     * This method checks whether the current player will occupy the box after his turn.
     *
     * @param i Indicates which player is currently at turn.
     * @return The number of boxes he occupied in his turn.
     */
    public int checkIfPlayerGetsField(int i) {
        if ((i % 2 == 0) && this.upLine && this.downLine && this.leftLine && this.rightLine) {
            this.filledByPlayer = FilledByPlayer.PLAYER1;
            return 1;
        }
        if ((i % 2 == 1) && this.upLine && this.downLine && this.leftLine && this.rightLine) {
            this.filledByPlayer = FilledByPlayer.PLAYER2;
            return 1;
        }
        return 0;
    }
}
