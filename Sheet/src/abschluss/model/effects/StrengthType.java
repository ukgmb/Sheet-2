package abschluss.model.effects;

/**
 * Describes how the value of the effect should be calculated. Currently only used in damage and heal effect.
 *
 * @author ukgmb
 */
public enum StrengthType {

    /**
     * Absolute value is used.
     */
    ABS,
    /**
     * Relative value as in percentage is used.
     */
    REL,
    /**
     * Base value is used.
     */
    BASE;

    private static final String PRINTABLE_NAME = "strength type";

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }
}
