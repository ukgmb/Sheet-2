package sheet_three;

enum GameType {
    AMERICAN,
    ICELANDIC,
    SWEDISH;
}

public class Field {
    GameType gameType;
    private char[][] field;
    private Box[][] allBoxes;

    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private static final char WHITESPACE = ' ';
    private static final char PLUS = '+';

    public Field(int n, GameType type) {
        this.gameType = type;
        this.field = new char[2 * n + 2][4 * n + 2];
        this.allBoxes = new Box[n][n];
        for (int a = 0; a < this.allBoxes.length; a++) {
            for (int b = 0; b < this.allBoxes[a].length; b++) {
                this.allBoxes[a][b] = new Box();
            }
        }
    }

    public void fillCharBlank() {
        for(int y = 0; y < this.field.length; y++) {
            for( int x = 0; x< this.field[y].length; x++) {
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
            for (int x = 1; x< this.field[y].length; x++) {
                if (((x % 4) == 1) && (y % 2) == 1) {
                    this.field[y][x] = PLUS;
                }
            }
        }

    }

    public void printField() {
        for(int y = 0; y < this.field.length; y++) {
            for( int x = 0; x< this.field[y].length; x++) {
                System.out.print(this.field[y][x]);
            }
            System.out.println();
        }
    }


}
