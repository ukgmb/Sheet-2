package sheet_three;

public class Field {

    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private static final char WHITESPACE = ' ';
    private static final char PLUS = '+';
    private static final char HORIZONTAL = '-';
    private static final char VERTICAL = '|';
    private static final String AMERICAN = "american";
    private static final String ICELANDIC = "icelandic";
    private static final String SWEDISH = "swedish";
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UP = "up";
    private static final String DOWN = "down";
    private static final char PLAYERA = 'A';
    private static final char PLAYERB = 'B';

    private char[][] field;
    private Box[][] allBoxes;

    public Field(int n, String gameType) {
        this.field = new char[2 * n + 2][4 * n + 2];
        this.allBoxes = new Box[n][n];
        for (int a = 0; a < this.allBoxes.length; a++) {
            for (int b = 0; b < this.allBoxes[a].length; b++) {
                this.allBoxes[a][b] = new Box();
            }
        }
        this.gamePreLayout(gameType.toLowerCase());

    }

    public Box[][] getAllBoxes() {
        return this.allBoxes;
    }

    public void fillCharBlank() {
        for (int y = 0; y < this.field.length; y++) {
            for (int x = 0; x < this.field[y].length; x++) {
                this.field[y][x] = WHITESPACE;
            }
        }

        int sumLetter = 0;
        for (int x = 0; x < this.field[0].length; x++) {
            if ((x % 4) == 3) {
                this.field[0][x] = ALPHABET[sumLetter];
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
                    this.field[y][x] = PLUS;
                }
            }
        }

    }

    public void printField() {
        for (int y = 0; y < this.allBoxes.length; y++) {
            for (int x = 0; x < this.allBoxes[y].length; x++) {
                int posY = 2 + (2 * y);
                int posX = 3 + (4 * x);
                if (this.allBoxes[y][x].getUpLine()) {
                    this.field[posY - 1][posX] = HORIZONTAL;
                }
                if (this.allBoxes[y][x].getDownLine()) {
                    this.field[posY + 1][posX] = HORIZONTAL;
                }
                if (this.allBoxes[y][x].getLeftLine()) {
                    this.field[posY][posX - 2] = VERTICAL;
                }
                if (this.allBoxes[y][x].getRightLine()) {
                    this.field[posY][posX + 2] = VERTICAL;
                }

                switch (this.allBoxes[y][x].getFilledByPlayer()) {
                    case PLAYER1:
                        this.field[posY][posX] = PLAYERA;
                        break;
                    case PLAYER2:
                        this.field[posY][posX] = PLAYERB;
                        break;
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

    public void gamePreLayout(String type) {
        if (type.equals(ICELANDIC)) {
            for (int y = 0; y < this.allBoxes.length; y++) {
                this.allBoxes[y][0].addLeftLine();
            }
            for (int x = 0; x < this.allBoxes[this.allBoxes.length - 1].length; x++) {
                this.allBoxes[this.allBoxes.length - 1][x].addDownLine();
            }
        }

        if (type.equals(SWEDISH)) {
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

    public void addLine(int x, int y, String side) {
        switch (side) {
            case UP:
                this.allBoxes[y][x].addUpLine();
                if (!(y == 0)) {
                    this.allBoxes[y -1][x].addDownLine();
                }
                break;
            case DOWN:
                this.allBoxes[y][x].addDownLine();
                if (!(y == (this.allBoxes.length - 1))) {
                    this.allBoxes[y + 1][x].addUpLine();
                }
                break;
            case LEFT:
                this.allBoxes[y][x].addLeftLine();
                if (!(x == (this.allBoxes.length - 1))) {
                    this.allBoxes[y][x - 1].addRightLine();
                }
                break;
            case RIGHT:
                this.allBoxes[y][x].addRightLine();
                if (!(x == 0)) {
                    this.allBoxes[y -1][x].addLeftLine();
                }
                break;
        }
    }
}
