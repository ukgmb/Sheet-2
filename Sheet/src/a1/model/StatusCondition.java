package a1.model;

/**
 * Represents the status condition of a {@code monster}.
 *
 * @author ukgmb
 */
public enum StatusCondition {

    /**
     * The wet condition affects the monster's effective defence.
     */
    WET,
    /**
     * The burn condition deals damage to the monster and affects its effective attack.
     */
    BURN,
    /**
     * The quick sand condition affects the monster's agility.
     */
    QUICK_SAND,
    /**
     * While the monster is affected by sleep, he can't perform an action.
     */
    SLEEP,
    /**
     * Monster isn't affected by any status condition.
     */
    OK;

    private static final String PRINTABLE_NAME = "status condition";

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }
}
