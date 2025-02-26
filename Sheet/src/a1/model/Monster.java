package a1.model;

import java.util.Set;

/**
 * Represents a monster in {@code Game}. Monsters are equipped with actions to battle each other in competitions.
 *
 * @author ukgmb
 */
public class Monster {

    private static final int DEFAULT_PRECISION_RATE = 1;
    private static final int DEFAULT_AGILITY_RATE = 1;

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
     * @param hitPoints Hit points
     * @param attackRate Rate of attack
     * @param defenceRate Rate of defence
     * @param speedRate Rate of speed
     */
    public Monster(String name, Element element,int hitPoints, int attackRate, int defenceRate, int speedRate,
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
}
