package sheetthree;

/**
 * Class Field represents the field of the game.
 *
 * @author ukgmb
 */
public class Field {



    private char[][] field;
    private Box[][] allBoxes;

    /**
     * Constructor creates the field with its pre Layout.
     *
     * @param fieldLength Length of the field
     * @param gameType Pre-Layout of the field
     */
    public Field(int fieldLength, String gameType) {
        this.field = new char[2 * fieldLength + 2][4 * fieldLength + 2];
        this.allBoxes = new Box[fieldLength][fieldLength];
        for (int a = 0; a < this.allBoxes.length; a++) {
            for (int b = 0; b < this.allBoxes[a].length; b++) {
                this.allBoxes[a][b] = new Box();
            }
        }
        this.gamePreLayout(gameType.toLowerCase());

    }

    /**
     * Returns all the boxes of the field.
     *
     * @return All Boxes in a two-dimensional array.
     */
    public Box[][] getAllBoxes() {
        return this.allBoxes;
    }

    /**
     * Fills the field with coordinates and box corners in the char array.
     */
    public void fillCharBlank() {
        for (int y = 0; y < this.field.length; y++) {
            for (int x = 0; x < this.field[y].length; x++) {
                this.field[y][x] = Constants.WHITESPACE;
            }
        }

        int sumLetter = 0;
        for (int x = 0; x < this.field[0].length; x++) {
            if ((x % 4) == 3) {
                this.field[0][x] = Constants.ALPHABET[sumLetter];
                sumLetter++;
            }
        }

        for (int y = 1; y < this.field.length; y++) {

            if ((y % 2) == 0) {
                int i = (int) (-0.5 + (0.5 * y) + 1);
                String save = String.valueOf(i);
                this.field[y][0] = save.charAt(0);
            }
        }

        for (int y = 1; y < this.field.length; y++) {
            for (int x = 1; x < this.field[y].length; x++) {
                if (((x % 4) == 1) && (y % 2) == 1) {
                    this.field[y][x] = Constants.PLUS;
                }
            }
        }

    }

    /**
     * Prints the current field which is stored in the char array.
     */
    public void printField() {
        for (int y = 0; y < this.allBoxes.length; y++) {
            for (int x = 0; x < this.allBoxes[y].length; x++) {
                int posY = 2 + (2 * y);
                int posX = 3 + (4 * x);
                if (this.allBoxes[y][x].getUpLine()) {
                    this.field[posY - 1][posX] = Constants.HORIZONTAL;
                }
                if (this.allBoxes[y][x].getDownLine()) {
                    this.field[posY + 1][posX] = Constants.HORIZONTAL;
                }
                if (this.allBoxes[y][x].getLeftLine()) {
                    this.field[posY][posX - 2] = Constants.VERTICAL;
                }
                if (this.allBoxes[y][x].getRightLine()) {
                    this.field[posY][posX + 2] = Constants.VERTICAL;
                }

                switch (this.allBoxes[y][x].getFilledByPlayer()) {
                    case PLAYER1:
                        this.field[posY][posX] = Constants.PLAYERA;
                        break;
                    case PLAYER2:
                        this.field[posY][posX] = Constants.PLAYERB;
                        break;
                    default:
                }
            }
        }

        for (int y = 0; y < this.field.length; y++) {
            for (int x = 0; x < this.field[y].length; x++) {
                System.out.print(this.field[y][x]);
            }
            System.out.println();
        }
    }

    /**
     * Fills the field according to the Pre-Layout.
     * @param type Pre-Layout Type (American, Icelandic or Swedish)
     */
    public void gamePreLayout(String type) {
        if (type.equals(Constants.ICELANDIC)) {
            for (int y = 0; y < this.allBoxes.length; y++) {
                this.allBoxes[y][0].addLeftLine();
            }
            for (int x = 0; x < this.allBoxes[this.allBoxes.length - 1].length; x++) {
                this.allBoxes[this.allBoxes.length - 1][x].addDownLine();
            }
        }

        if (type.equals(Constants.SWEDISH)) {
            for (int y = 0; y < this.allBoxes.length; y++) {
                this.allBoxes[y][0].addLeftLine();
            }
            for (int x = 0; x < this.allBoxes[this.allBoxes.length - 1].length; x++) {
                this.allBoxes[this.allBoxes.length - 1][x].addDownLine();
            }
            for (int y = 0; y < this.allBoxes.length; y++) {
                this.allBoxes[y][this.allBoxes[y].length - 1].addRightLine();
            }
            for (int x = 0; x < this.allBoxes[this.allBoxes.length - 1].length; x++) {
                this.allBoxes[0][x].addUpLine();
            }
        }

    }

    /**
     * Method adds the line in the field and then checks if the player will occupy the box.
     *
     * @param x x-coordinate of the box (a - j in the field)
     * @param y y-coordinate of the box (1 - 9 in the field)
     * @param side The side of the box (Up, down, left or right)
     * @param i Indicates which player is currently at turn (i even means player A, i uneven means player B)
     * @return The amount of boxes which the current player additionally occupied.
     */
    public int addLineAndCheckIfPlayerGetsBox(int x, int y, String side, int i) {
        int success = 0;
        switch (side) {
            case Constants.UP:
                this.allBoxes[y][x].addUpLine();
                success = success + this.allBoxes[y][x].checkIfPlayerGetsField(i);
                if (!(y == 0)) {
                    this.allBoxes[y - 1][x].addDownLine();
                    success = success + this.allBoxes[y - 1][x].checkIfPlayerGetsField(i);
                }
                break;
            case Constants.DOWN:
                this.allBoxes[y][x].addDownLine();
                success = success + this.allBoxes[y][x].checkIfPlayerGetsField(i);
                if (!(y == (this.allBoxes.length - 1))) {
                    this.allBoxes[y + 1][x].addUpLine();
                    success = success + this.allBoxes[y + 1][x].checkIfPlayerGetsField(i);
                }
                break;
            case Constants.LEFT:
                this.allBoxes[y][x].addLeftLine();
                success = success + this.allBoxes[y][x].checkIfPlayerGetsField(i);
                if (!(x == 0)) {
                    this.allBoxes[y][x - 1].addRightLine();
                    success = success + this.allBoxes[y][x - 1].checkIfPlayerGetsField(i);
                }
                break;
            case Constants.RIGHT:
                this.allBoxes[y][x].addRightLine();
                success = success + this.allBoxes[y][x].checkIfPlayerGetsField(i);
                if (!(x == this.allBoxes.length - 1)) {
                    this.allBoxes[y][x + 1].addLeftLine();
                    success = success + this.allBoxes[y][x + 1].checkIfPlayerGetsField(i);
                }
                break;
            default:
        }
        return success;
    }
}
