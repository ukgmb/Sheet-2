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
            } else {
                break;
            }
        }
        while (true) {
            if (args[1].toLowerCase().equals(AMERICAN) || args[1].toLowerCase().equals(SWEDISH)) {
                break;
            } else if (args[1].toLowerCase().equals(ICELANDIC)) {
                break;
            } else {
                System.out.println("Wrong input! PreLayout must be American, Icelandic or Swedish");
                args[1] = scanner.nextLine();
            }
        }
        Field field = new Field(Integer.parseInt(args[0]), args[1].toLowerCase());
        field.fillCharBlank();
        field.printField();

        int[] sumBox = new int[2];
        int i = 0;

        while (i >= 0) {
            if ((i % 2) == 0) {
                System.out.println("Player A:");
            } else {
                System.out.println("Player B:");
            }

            String input = scanner.nextLine();
            int boxX = 0;
            int boxY = 0;
            String boxSide = "";

            while (true) {
                input = input.trim();
                while (true) {
                    if (input.length() > 4) {
                        break;
                    } else {
                        System.out.println("Wrong input! Enter Again.");
                        input = scanner.nextLine();
                    }
                }

                char letter = input.charAt(0);
                char num = input.charAt(1);
                int number = Character.getNumericValue(num);
                int success = 0;
                for (int x = 0; x < Integer.parseInt(args[0]); x++) {
                    if (letter == ALPHABET[x]) {
                        success++;
                        boxX = x;
                    }
                }
                if ((number > 0) && (number < (Integer.parseInt(args[0]) + 1))) {
                    success++;
                    boxY = number - 1;
                }
                input = input.substring(3);
                input = input.trim();


                switch (input.toLowerCase()) {
                    case UP:
                        if (!field.getAllBoxes()[boxY][boxX].getUpLine()) {
                            success++;
                            boxSide = input.toLowerCase();
                        }
                        break;
                    case DOWN:
                        if (!field.getAllBoxes()[boxY][boxX].getDownLine()) {
                            success++;
                            boxSide = input.toLowerCase();
                        }
                        break;
                    case LEFT:
                        if (!field.getAllBoxes()[boxY][boxX].getLeftLine()) {
                            success++;
                            boxSide = input.toLowerCase();
                        }
                        break;
                    case RIGHT:
                        if (!field.getAllBoxes()[boxY][boxX].getRightLine()) {
                            success++;
                            boxSide = input.toLowerCase();
                        }
                        break;
                }


                if (success == 3) {
                    break;
                }
                else if ( success == 2) {
                    System.out.println("Your entered line is already used! Enter Again.");
                    input = scanner.nextLine();
                }
                else {
                    System.out.println("Wrong input! Enter Again.");
                    input = scanner.nextLine();
                }

            }

            field.addLine(boxX, boxY, input.toLowerCase());
            if (field.getAllBoxes()[boxY][boxX].checkIfPlayerGetsField(i)) {
                sumBox[i % 2]++;
                i = i - 1;
            }
            field.printField();

            i++;
        }


    }
}
