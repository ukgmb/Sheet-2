package sheetfour;

import java.util.Scanner;

public class NavigationSystem {

    public Terrain terrain;

    public void runNavigationSystem() {
        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();

        String input;
        do {
            input = scanner.nextLine();
        }
        while (!commands.isNewInputSyntaxCorrect(input));

        if (commands.getCommandType().equals(CommandType.NEW_TERRAIN)) {
            this.terrain = new Terrain(4, 4);
            this.terrain.readTerrain();
        }


    }
}
