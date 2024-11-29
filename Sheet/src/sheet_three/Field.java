package sheet_three;

enum GameType {
    AMERICAN,
    ICELANDIC,
    SWEDISH;
}

public class Field {
    private int fieldLength;
    GameType gameType;
    private char[][] field;
    private Box[][] allBoxes;

    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};

    public Field(int n, GameType type) {
        this.fieldLength = n;
        this.gameType = type;
        this.field = new char[4 * n + 2][2 * n + 2];
        this.allBoxes = new Box[n][n];
        for (int a = 0; a < this.allBoxes.length; a++) {
            for (int b = 0; b < this.allBoxes[a].length; b++) {
                this.allBoxes[a][b] = new Box();
            }
        }
    }

    public void fillCharBlank() {
        int sumLetter = 0;
        for (int x = 0; x < this.field.length; x++) {
            if ((x % 4) == 3) {
                field[0][x] = ALPHABET[sumLetter];
                sumLetter++;
            }
        }

        for (int y = 3; y < this.field.length; y++) {
            if ((y % 2) == 1) {
                int i = (int) (-0.5 + (0.5 * y));
                String save = String.valueOf(i);
                field[y][0] = save.charAt(0);
            }
        }

    }

    public void printField() {
        for(int y = 0; y < this.field.length; y++) {
            for( int x = 0; x< this.field[y].length; x++) {
                System.out.print(field[y][x]);
            }
            System.out.println();
        }
    }


}
