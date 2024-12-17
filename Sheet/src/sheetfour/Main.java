package sheetfour;

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
