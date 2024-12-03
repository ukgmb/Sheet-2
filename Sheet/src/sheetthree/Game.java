package sheetthree;

import java.util.Scanner;

/**
 * Class Game contains all the game mechanics and rules.
 *
 * @author ukgmb
 */
public class Game {

    /**
     * Starts and runs the game.
     *
     * @param args The field length and pre-Layout of the field.
     */
    public void runGame(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] newArgs = checkStartInputs(args, scanner).clone();

        Field field = new Field(Integer.parseInt(newArgs[0]), newArgs[1].toLowerCase());
        field.fillCharBlank();
        field.printField();

        int[] sumBox = {0, 0};
        int i = 0;

        boolean quit = false;

        while (i >= 0) {
            printPlayer(i);

            String input = scanner.nextLine();

            if (input.toLowerCase().equals(Constants.QUIT)) {
                quit = true;
                break;
            }

            int boxX = 0;
            int boxY = 0;

            int success = 0;

            while (success < 3) {
                input = input.trim();
                input = checkPlayerInputLength(input, scanner);

                String[] allInputs = checkPlayerInputContent(input, field, newArgs[0], scanner);
                boxX = Integer.parseInt(allInputs[0]);
                boxY = Integer.parseInt(allInputs[1]);
                input = allInputs[2];
                success = Integer.parseInt(allInputs[3]);
                if (success == 3) {
                    break;
                }

            }

            if (field.addLineAndCheckIfPlayerGetsBox(boxX, boxY, input, i) > 0) {
                sumBox[i % 2] = sumBox[i % 2] + field.addLineAndCheckIfPlayerGetsBox(boxX, boxY, input, i);
                i = i - 1;
            }
            field.printField();

            i++;

            if ((sumBox[0] + sumBox[1]) == (Integer.parseInt(newArgs[0]) * Integer.parseInt(newArgs[0]))) {
                break;
            }
        }
        scanner.close();
        gameOver(sumBox[0], sumBox[1], quit);
    }


    /**
     * Checks the game inputs for the game length and pre-Layout.
     * If not correct, the player will have to enter until it is correct.
     *
     * @param args    The field length and pre-Layout input to be checked.
     * @param scanner Scanner for the new player input, in case the original input is incorrect.
     * @return Returns the correct inputs (Game length and pre-Layout).
     */
    private static String[] checkStartInputs(String[] args, Scanner scanner) {
        while (true) {
            if (Integer.parseInt(args[0]) < 1 || Integer.parseInt(args[0]) > 9) {
                System.out.println("Wrong input! Field lenght must be between 2 and 9:");
                args[0] = scanner.nextLine();
            } else {
                break;
            }
        }
        while (true) {
            if (args[1].toLowerCase().equals(Constants.AMERICAN) || args[1].toLowerCase().equals(Constants.SWEDISH)) {
                break;
            } else if (args[1].toLowerCase().equals(Constants.ICELANDIC)) {
                break;
            } else {
                System.out.println("Wrong input! PreLayout must be American, Icelandic or Swedish");
                args[1] = scanner.nextLine();
            }
        }

        return args;
    }

    /**
     * Checks whether the input of the player has a length of minimum 4.
     * If check failed, player needs to enter new input until correct.
     *
     * @param input   The input of the player.
     * @param scanner Scanner for the new player input, in case the original input is incorrect.
     * @return Correct input.
     */
    private static String checkPlayerInputLength(String input, Scanner scanner) {
        String newInput = input;
        while (true) {
            if (newInput.length() > 4) {
                break;
            } else {
                System.out.println("Wrong input! Enter Again.");
                newInput = scanner.nextLine();
            }
        }

        return newInput;
    }

    /**
     * Checks the content of the player's input.
     * If check failed, then player needs to enter again until it is correct.
     *
     * @param input       The first input of the player.
     * @param field       The field of the game.
     * @param fieldLength The length of the field.
     * @param scanner     Scanner for the new player input, in case the original input is incorrect.
     * @return The correct input of the player.
     */
    public static String[] checkPlayerInputContent(String input, Field field, String fieldLength, Scanner scanner) {
        String[] correctInput = {"x", "y", "side", "success"};
        int boxX = 0;
        int boxY = 0;
        char letter = input.charAt(0);
        char num = input.charAt(1);
        int number = Character.getNumericValue(num);
        int success = 0;
        for (int x = 0; x < Integer.parseInt(fieldLength); x++) {
            if (letter == Constants.ALPHABET[x]) {
                success++;
                boxX = x;
            }
        }
        if ((number > 0) && (number < (Integer.parseInt(fieldLength) + 1))) {
            success++;
            boxY = number - 1;
        }
        String newInput = input;
        newInput = newInput.substring(2);
        newInput = newInput.trim();
        newInput = newInput.toLowerCase();
        switch (newInput) {
            case Constants.UP:
                if (!field.getAllBoxes()[boxY][boxX].getUpLine()) {
                    success++;
                }
                break;
            case Constants.DOWN:
                if (!field.getAllBoxes()[boxY][boxX].getDownLine()) {
                    success++;
                }
                break;
            case Constants.LEFT:
                if (!field.getAllBoxes()[boxY][boxX].getLeftLine()) {
                    success++;
                }
                break;
            case Constants.RIGHT:
                if (!field.getAllBoxes()[boxY][boxX].getRightLine()) {
                    success++;
                }
                break;
            default:
        }

        if (success == 2) {
            System.out.println("Your entered line is already used! Enter Again.");
            newInput = scanner.nextLine();
        } else if (success < 2) {
            System.out.println("Wrong input! Enter Again.");
            newInput = scanner.nextLine();
        }

        correctInput[0] = Integer.toString(boxX);
        correctInput[1] = Integer.toString(boxY);
        correctInput[2] = newInput;
        correctInput[3] = Integer.toString(success);
        return correctInput;
    }

    /**
     * Prints the game over text.
     * Prints the winner of the game or when the game is quited earlier.
     *
     * @param sumA Number of boxes occupied by Player A.
     * @param sumB Number of boxes occupied by Player B.
     * @param quit Boolean value indicates if game is quited or not.
     */
    public static void gameOver(int sumA, int sumB, boolean quit) {
        if (quit) {
            System.out.println("Game quit");
        } else if (sumA < sumB) {
            System.out.println("Game over. Player B wins");
        } else if (sumA > sumB) {
            System.out.println("Game over. Player A wins");
        } else {
            System.out.println("Game Over. Tie!");
        }
    }

    /**
     * Prints which player is currently at turn.
     *
     * @param i If i is even: Player A is at turn. If i is uneven: Player B is at turn.
     */
    public static void printPlayer(int i) {
        if ((i % 2) == 0) {
            System.out.println("Player A:");
        } else {
            System.out.println("Player B:");
        }
    }
}