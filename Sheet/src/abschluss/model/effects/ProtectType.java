package abschluss.model.effects;

/**
 * Describes the type of stat protected by {@code EffectStatProtect}.
 *
 * @author ukgmb
 */
public enum ProtectType {

    /**
     * The health condition of the monster will be protected.
     */
    HEALTH(" is now protected against damage!"),
    /**
     * The stats of the monster will be protected.
     */
    STATS(" is now protected against status changes");

    private static final String PRINTABLE_NAME = "protection";

    private final String messageHit;

    ProtectType(String messageHit) {
        this.messageHit = messageHit;
    }

    /**
     * Returns the message when protection hits the monster.
     * @return The message as a string
     */
    public String getMessageHit() {
        return messageHit;
    }

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }

}
