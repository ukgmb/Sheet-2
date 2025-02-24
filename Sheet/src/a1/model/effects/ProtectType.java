package a1.model.effects;

/**
 * Describes the type of stat protected by {@code EffectStatProtect}.
 *
 * @author ukgmb
 */
public enum ProtectType {

    /**
     * The health condition of the monster will be protected.
     */
    HEALTH,
    /**
     * The stats of the monster will be protected.
     */
    STATS;

    private static final String PRINTABLE_NAME = "protection";

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }
}
