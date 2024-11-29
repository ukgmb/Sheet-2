package sheet_three;

import java.util.Scanner;

public final class Game {

    private Game() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Field field = new Field(4, "Icelandic");
        field.fillCharBlank();
        field.printField();


    }
}
