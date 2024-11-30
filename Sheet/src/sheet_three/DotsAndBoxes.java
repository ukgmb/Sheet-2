package sheet_three;

import java.util.Scanner;

public final class DotsAndBoxes {

    private static final String AMERICAN = "american";
    private static final String ICELANDIC = "icelandic";
    private static final String SWEDISH = "swedish";
    private static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
    private static final String LEFT = "left";
    private static final String RIGHT = "right";
    private static final String UP = "up";
    private static final String DOWN = "down";

    private DotsAndBoxes() {

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 9) {
                System.out.println("Wrong input! Field lenght must be between 2 and 9:");
                args[0] = scanner.nextLine();
            }
            else {
                break;
            }
        }
        while (true) {
            if (args[1].toLowerCase().equals(AMERICAN) || args[1].toLowerCase().equals(SWEDISH)) {
                break;
            }
            else if (args[1].toLowerCase().equals(ICELANDIC)) {
                break;
            }
            else {
                System.out.println("Wrong input! PreLayout must be American, Icelandic or Swedish");
                args[1] = scanner.nextLine();
            }
        }
        Field field = new Field(Integer.parseInt(args[0]), args[1].toLowerCase());
        field.fillCharBlank();
        field.printField();

        int sumBoxA = 0;
        int sumBoxB = 0;
        int i = 0;

        if (i >= 0) {
            if ((i % 2) == 0) {
                System.out.println("Player A:");
            }
            else {
                System.out.println("Player B:");
            }

            String input = scanner.nextLine();
            while (true) {
                input = input.trim();
                char letter = input.charAt(0);
                char number = input.charAt(1);
                int success = 0;
                for (int x = 0; x < Integer.parseInt(args[0]); x++) {
                    if (letter == ALPHABET[x]) {
                        success++;
                    }
                }
                if (number >= 1 && number <= Integer.parseInt(args[0])) {
                    success++;
                }
                input = input.substring(3);
                input = input.trim();
                if (input.toLowerCase().equals(UP) || input.toLowerCase().equals(DOWN)) {
                    success++;
                }
                if (input.toLowerCase().equals(LEFT) || input.toLowerCase().equals(RIGHT)) {
                    success++;
                }

                if (success == 3) {
                    break;
                }
                else {
                    System.out.println("Wrong input! Enteragain");
                    input = scanner.nextLine();
                }

            }


            i++;
        }


    }
}
