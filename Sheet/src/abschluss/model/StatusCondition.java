package abschluss.model;

/**
 * Represents the status condition of a {@code monster}.
 *
 * @author ukgmb
 */
public enum StatusCondition {

    /**
     * The wet condition affects the monster's effective defence.
     */
    WET(" becomes soaking wet!", " is soaking wet!", " dried up!"),
    /**
     * The burn condition deals damage to the monster and affects its effective attack.
     */
    BURN(" caught on fire!", " is burning", "'s burning has faded"),
    /**
     * The quick sand condition affects the monster's agility.
     */
    QUICK_SAND(" gets caught in quicksand!", " is caught in quicksand", " escaped quicksand!"),
    /**
     * While the monster is affected by sleep, he can't perform an action.
     */
    SLEEP(" falls asleep", " is asleep", " woke up!"),
    /**
     * Monster isn't affected by any status condition.
     */
    OK(null, null, null);

    private static final String PRINTABLE_NAME = "status condition";

    private final String messageHit;
    private final String messageStatus;
    private final String messageOver;

    StatusCondition(String messageHit, String messageStatus, String messageOver) {
        this.messageHit = messageHit;
        this.messageStatus = messageStatus;
        this.messageOver = messageOver;
    }

    /**
     * Returns the message when monsters gets hit by the status condition.
     * @return The corresponding message as string
     */
    public String getMessageHit() {
        return messageHit;
    }

    /**
     * Returns the message when monsters still suffers under the status condition.
     * @return The corresponding message as string
     */
    public String getMessageStatus() {
        return messageStatus;
    }

    /**
     * Returns the message when monsters ends the suffering.
     * @return The corresponding message as string
     */
    public String getMessageOver() {
        return messageOver;
    }

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }
}
