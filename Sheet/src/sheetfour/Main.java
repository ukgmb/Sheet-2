package sheetfour;

/**
 * Main class used to run the navigation system.
 * @author ukgmb
 */
public final class Main {

    private Main() {

    }

    public static void main(String[] args) {
        NavigationSystem navigation = new NavigationSystem();
        navigation.runNavigationSystem();
        navigation.terrain.printTerrain();
        navigation.terrain.checkForValidTerrain();
    }
}
