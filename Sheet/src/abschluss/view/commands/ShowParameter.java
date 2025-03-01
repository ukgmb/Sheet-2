package abschluss.view.commands;

/**
 * This enum represents all the parameters the {@link CommandShow} could have.
 *
 * @author ukgmb
 */
public enum ShowParameter {


    /**
     * The show command shows the possible actions of the current monster in the competition.
     */
    ACTIONS,
    /**
     * The show command shows the stats of the current monster in the competition.
     */
    STATS;

    /**
     * Returns whether the parameter matches the given input.
     * @param input the input to be matched
     * @return whether the parameter matches the input
     */
    public boolean matches(String input) {
        return name().toLowerCase().equals(input);
    }
}
