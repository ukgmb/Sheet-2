package sheet_three;

public class Game {

    public static void main(String[] args) {
        Field field = new Field(4, GameType.AMERICAN);
        field.fillCharBlank();
        field.printField();
    }
}
