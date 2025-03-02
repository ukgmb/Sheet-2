package abschluss.model;

import abschluss.model.effects.Stat;

import java.util.StringJoiner;

/**
 * This class represents the stats of a {@link Monster} including ATK, DEF, SPD, PRC and AGL.
 *
 * @author ukgmb
 */
public class MonsterStats {

    private static final int DEFAULT_PRECISION_RATE = 1;
    private static final int DEFAULT_AGILITY_RATE = 1;
    private static final int DEFAULT_STAT_CHANGE = 0;

    private static final String STATS_SEPARATOR = ", ";
    private static final String STAT_VALUE_SEPARATOR = " ";
    private static final String STAT_CHANGE = "(%s)";

    private final int attackRate;
    private final int defenceRate;
    private final int speedRate;
    private final int precisionRate;
    private final int agilityRate;
    private int attackChange;
    private int defenceChange;
    private int speedChange;
    private int precisionChange;
    private int agilityChange;

    /**
     * Constructs a new monster stats instance with its parameters.
     * @param attackRate Attack rate of the monster
     * @param defenceRate Defence rate of the monster
     * @param speedRate Speed rate of the monster
     */
    protected MonsterStats(int attackRate, int defenceRate, int speedRate) {
        this.attackRate = attackRate;
        this.defenceRate = defenceRate;
        this.speedRate = speedRate;
        this.precisionRate = DEFAULT_PRECISION_RATE;
        this.agilityRate = DEFAULT_AGILITY_RATE;

        this.attackChange = DEFAULT_STAT_CHANGE;
        this.defenceChange = DEFAULT_STAT_CHANGE;
        this.speedChange = DEFAULT_STAT_CHANGE;
        this.precisionChange = DEFAULT_STAT_CHANGE;
        this.agilityChange = DEFAULT_STAT_CHANGE;
    }

    /**
     * A copy constructor to copy the stats.
     * @param stats The stats to be copied
     */
    protected MonsterStats(MonsterStats stats) {
        this.attackRate = stats.attackRate;
        this.defenceRate = stats.defenceRate;
        this.speedRate = stats.speedRate;
        this.precisionRate = stats.precisionRate;
        this.agilityRate = stats.agilityRate;

        this.attackChange = stats.attackChange;
        this.defenceChange = stats.defenceChange;
        this.speedChange = stats.speedChange;
        this.precisionChange = stats.precisionChange;
        this.agilityChange = stats.agilityChange;
    }

    /**
     * Returns the stats ATK, DEF and SPD as a string.
     * @return String containing the information
     */
    protected String getMainStats() {
        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR);

        joiner.add(Stat.ATK.name() + STAT_VALUE_SEPARATOR + this.attackRate);
        joiner.add(Stat.DEF.name() + STAT_VALUE_SEPARATOR + this.defenceRate);
        joiner.add(Stat.SPD.name() + STAT_VALUE_SEPARATOR + this.speedRate);

        return joiner.toString();
    }

    /**
     * Returns all the stats with their stat change factor as a string.
     * @return String containing all the information.
     */
    protected String getAllStatsWithChange() {
        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR);

        joiner.add(Stat.ATK.name() + STAT_VALUE_SEPARATOR + this.attackRate
                + (this.attackChange != DEFAULT_STAT_CHANGE ? STAT_CHANGE.formatted(this.attackChange) : ""));
        joiner.add(Stat.DEF.name() + STAT_VALUE_SEPARATOR + this.defenceRate
                + (this.defenceChange != DEFAULT_STAT_CHANGE ? STAT_CHANGE.formatted(this.defenceChange) : ""));
        joiner.add(Stat.SPD.name() + STAT_VALUE_SEPARATOR + this.speedRate
                + (this.speedChange != DEFAULT_STAT_CHANGE ? STAT_CHANGE.formatted(this.speedChange) : ""));
        joiner.add(Stat.PRC.name() + STAT_VALUE_SEPARATOR + this.precisionRate
                + (this.precisionChange != DEFAULT_STAT_CHANGE ? STAT_CHANGE.formatted(this.precisionChange) : ""));
        joiner.add(Stat.AGL.name() + STAT_VALUE_SEPARATOR + this.agilityRate
                + (this.agilityChange != DEFAULT_STAT_CHANGE ? STAT_CHANGE.formatted(this.agilityChange) : ""));

        return joiner.toString();
    }

    /**
     * Returns the stat needed.
     * @param stat The type of stat
     * @return The value of the needed stat
     */
    protected int getStat(Stat stat) {
        return switch (stat) {
            case ATK -> this.attackRate;
            case DEF -> this.defenceRate;
            case SPD -> this.speedRate;
            case PRC -> this.precisionRate;
            case AGL -> this.agilityRate;
        };
    }

    /**
     * Returns the change stat needed.
     * @param stat The type of stat
     * @return The value of the needed change stat
     */
    protected int getChangeStat(Stat stat) {
        return switch (stat) {
            case ATK -> this.attackChange;
            case DEF -> this.defenceChange;
            case SPD -> this.speedChange;
            case PRC -> this.precisionChange;
            case AGL -> this.agilityChange;
        };
    }

    /**
     * Sets the change stat.
     * @param stat Type of stat
     * @param change The new change value of the stat
     */
    protected void setStat(Stat stat, int change) {
        switch (stat) {
            case ATK -> this.attackChange = change;
            case DEF -> this.defenceChange = change;
            case SPD -> this.speedChange = change;
            case PRC -> this.precisionChange = change;
            case AGL -> this.agilityChange = change;
            default -> System.out.println();
        }
    }
}
