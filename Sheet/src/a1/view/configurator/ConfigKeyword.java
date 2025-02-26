package a1.view.configurator;

/**
 * Describes all the keywords uses during configuration.
 *
 * @author ukgmb
 */
public enum ConfigKeyword {

    /**
     * Actions are declared during configuration.
     */
    ACTION(true),
    /**
     * Monsters are declared during configuration.
     */
    MONSTER(false);

    private final boolean requiresMoreLines;

    ConfigKeyword(boolean requiresMoreLines) {
        this.requiresMoreLines = requiresMoreLines;
    }

    /**
     * Checks whether the input string matches to the enum value.
     * @param input The string input to be matched
     * @return {@code true}, if match was found. Else, returns {@code false}
     */
    public boolean matches(String input) {
        return this.name().toLowerCase().equals(input);
    }

    /**
     * Returns if more lines are necessary or not.
     * @return {@code true}, if necessary. Else, returns {@code false}
     */
    public boolean requiresMoreLines() {
        return this.requiresMoreLines;
    }
}
