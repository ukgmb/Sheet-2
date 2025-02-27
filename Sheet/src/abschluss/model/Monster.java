package abschluss.model;

import abschluss.model.effects.Stat;

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
    private static final String FAINTED_CONDITION = "FAINTED";
    private static final int HP_NEEDED_FOR_FAINTED = 0;
    private static final String CURRENT_CONDITION_FORMAT = "(%s)";
    private static final String HEALTH_BAR_DELIMITER = "";
    private static final String HEALTH_BAR_PREFIX = "[";
    private static final String HEALTH_BAR_SUFFIX = "]";
    private static final int HEALTH_BAR_LENGTH = 20;
    private static final String HEALTH_REPRESENTATION = "X";
    private static final String HEALTH_LOSS_REPRESENTATION = "_";
    private static final String ARGUMENT_SEPARATOR = " ";
    private static final String PLACEHOLDER_STATUS = "%s"; //Placeholder to get filled later by competition class.

    private String name;

    private final int maxHitPoints;

    private final int hitPoints;
    private final int attackRate;
    private final int defenceRate;
    private final int speedRate;
    private final int precision;
    private final int agilityRate;

    private final Element element;
    private final Set<Action> actions;
    private final StatusCondition condition;

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

        this.maxHitPoints = hitPoints;

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
     * A copy constructor to copy a monster.
     * @param monster The monster to be copied
     */
    public Monster(Monster monster) {
        this.name = monster.name;

        this.maxHitPoints = monster.hitPoints;

        this.hitPoints = monster.hitPoints;
        this.attackRate = monster.attackRate;
        this.defenceRate = monster.defenceRate;
        this.speedRate = monster.speedRate;
        this.agilityRate = monster.agilityRate;
        this.precision = monster.precision;

        this.element = monster.element;
        this.actions = Set.copyOf(monster.actions);
        this.condition = monster.condition;
    }

    /**
     * Returns the name of the monster.
     * @return The name of the monster.
     */
    public String getName() {
        return this.name;
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

    /**
     * Adds a String value to the monsters name.
     * @param suffix Suffix to be added to name
     */
    public void addNameSuffix(String suffix) {
        this.name += suffix;
    }

    /**
     * Constructs a String that shows the monsters current status during competition.
     * @return The string showing the monsters current status during competition
     */
    public String getStatus() {
        StringJoiner joiner = new StringJoiner(ARGUMENT_SEPARATOR);

        joiner.add(healthBar());
        joiner.add(PLACEHOLDER_STATUS);
        joiner.add(PLACEHOLDER_STATUS + this.name);
        joiner.add(currentCondition());

        return joiner.toString();

    }

    private String healthBar() {
        StringJoiner joiner = new StringJoiner(HEALTH_BAR_DELIMITER, HEALTH_BAR_PREFIX, HEALTH_BAR_SUFFIX);
        int countX = (int) Math.ceil(HEALTH_BAR_LENGTH * ((double) this.hitPoints / this.maxHitPoints));
        int countNotX = HEALTH_BAR_LENGTH - countX;

        for (int i = 0; i < countX; i++) {
            joiner.add(HEALTH_REPRESENTATION);
        }
        for (int i = 0; i < countNotX; i++) {
            joiner.add(HEALTH_LOSS_REPRESENTATION);
        }

        return joiner.toString();
    }

    private String currentCondition() {
        if (isFainted()) {
            return CURRENT_CONDITION_FORMAT.formatted(FAINTED_CONDITION);
        }
        return CURRENT_CONDITION_FORMAT.formatted(this.condition.name());

    }

    private boolean isFainted() {
        return this.hitPoints == HP_NEEDED_FOR_FAINTED;
    }




}
