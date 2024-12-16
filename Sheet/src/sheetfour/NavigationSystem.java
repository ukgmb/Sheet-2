package sheetfour;

public class NavigationSystem {

    public static void main(String[] args) {

        Commands commands = new Commands();

        commands.scanForInput();
        System.out.println(commands.isNewInputSyntaxCorrect());
        System.out.println(commands.getInputType());


    }
}
