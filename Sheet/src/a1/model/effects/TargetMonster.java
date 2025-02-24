package a1.model.effects;

/**
 * Describes the target monster for an {@code Effect}.
 *
 * @author ukgmb
 */
public enum TargetMonster {

    /**
     * The target is the monster which uses the effect.
     */
    USER,
    /**
     * The target is the opposing monster.
     */
    TARGET;

    private static final String PRINTABLE_NAME = "target";

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }


}
