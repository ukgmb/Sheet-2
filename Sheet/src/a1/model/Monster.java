package a1.model;

import a1.model.effects.Stat;

import java.util.Set;
import java.util.StringJoiner;

/**
 * Represents a monster in {@code Game}. Monsters are equipped with actions to battle each other in competitions.
 *
 * @author ukgmb
 */
public class Monster {

    private static final int DEFAULT_PRECISION_RATE = 1;
    private static final int DEFAULT_AGILITY_RATE = 1;

    private static final String NAME_STATS_SEPARATOR = ": ";
    private static final String STATS_SEPARATOR = ", ";
    private static final String EMPTY_SUFFIX = "";
    private static final String STAT_VALUE_SEPARATOR = " ";
    private static final String SHORT_HIT_POINTS = "HP";

    private final String name;

    private int hitPoints;
    private int attackRate;
    private int defenceRate;
    private int speedRate;
    private int precision;
    private int agilityRate;

    private final Element element;
    private final Set<Action> actions;
    private StatusCondition condition;

    /**
     * Constructs a new monster. Declaration of HitPoints, AttackRate, DefenceRate and SpeedRate are required.
     * @param name Name of the monster
     * @param element Element of the monster
     * @param hitPoints Hit points
     * @param attackRate Rate of attack
     * @param defenceRate Rate of defence
     * @param speedRate Rate of speed
     * @param actions All the actions the monster can do (max 4 actions allowed)
     */
    public Monster(String name, Element element, int hitPoints, int attackRate, int defenceRate, int speedRate,
                   Set<Action> actions) {
        this.name = name;

        this.hitPoints = hitPoints;
        this.attackRate = attackRate;
        this.defenceRate = defenceRate;
        this.speedRate = speedRate;
        this.precision = DEFAULT_PRECISION_RATE;
        this.agilityRate = DEFAULT_AGILITY_RATE;

        this.element = element;
        this.actions = Set.copyOf(actions);
        this.condition = StatusCondition.OK;

    }

    /**
     * Returns the name of the monster.
     * @return The name of the monster.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the monster stats(Element, HP, ATK, DEF, SPD).
     * @return Stats of the monster
     */
    public String getStats() {
        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR, this.name + NAME_STATS_SEPARATOR, EMPTY_SUFFIX);

        joiner.add(Element.class.getSimpleName() + STAT_VALUE_SEPARATOR + this.element.name());
        joiner.add(SHORT_HIT_POINTS  +  STAT_VALUE_SEPARATOR + this.hitPoints);
        joiner.add(Stat.ATK.name() + STAT_VALUE_SEPARATOR + this.attackRate);
        joiner.add(Stat.DEF.name() + STAT_VALUE_SEPARATOR + this.defenceRate);
        joiner.add(Stat.SPD.name() + STAT_VALUE_SEPARATOR + this.speedRate);

        return joiner.toString();
    }
}
