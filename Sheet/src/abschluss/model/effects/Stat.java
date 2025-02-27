package abschluss.model.effects;

/**
 * Represents the stats of an {@code Monster} which can be influenced by an {@code Effect}.
 *
 * @author ukgmb
 */
public enum Stat {

    /**
     * Attack stat of a monster.
     */
    ATK,
    /**
     * Defence stat of a monster.
     */
    DEF,
    /**
     * Speed stat of a monster.
     */
    SPD,
    /**
     * Precision stat of a monster.
     */
    PRC,
    /**
     * Agility stat of a monster.
     */
    AGL;

    private static final String PRINTABLE_NAME = "stat";

    @Override
    public String toString() {
        return PRINTABLE_NAME;
    }
}
