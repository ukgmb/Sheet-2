package sheet_three;

import java.util.Scanner;

public final class DotsAndBoxes {

    private static final String AMERICAN = "american";
    private static final String ICELANDIC = "icelandic";
    private static final String SWEDISH = "swedish";

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


    }
}
