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

    private static final String SHOW_ACTIONS_MONSTER_NAME = "ACTIONS OF %s";

    private static final String SHOW_STATS_MONSTER_NAME = "STATS OF %s";
    private static final String SHOW_STATS_HEALTH_SEPARATOR = "/";

    private String name;

    private final int maxHitPoints;

    private int hitPoints;
    private final int attackRate;
    private final int defenceRate;
    private final int speedRate;
    private final int precisionRate;
    private final int agilityRate;

    private final Element element;
    private final Set<Action> actions;
    private final StatusCondition condition;

    /**
     * Constructs a new monster. Declaration of HitPoints, AttackRate, DefenceRate and SpeedRate are required.
     *
     * @param name        Name of the monster
     * @param element     Element of the monster
     * @param hitPoints   Hit points
     * @param attackRate  Rate of attack
     * @param defenceRate Rate of defence
     * @param speedRate   Rate of speed
     * @param actions     All the actions the monster can do (max 4 actions allowed)
     */
    public Monster(String name, Element element, int hitPoints, int attackRate, int defenceRate, int speedRate,
                   Set<Action> actions) {
        this.name = name;

        this.maxHitPoints = hitPoints;

        this.hitPoints = hitPoints;
        this.attackRate = attackRate;
        this.defenceRate = defenceRate;
        this.speedRate = speedRate;
        this.precisionRate = DEFAULT_PRECISION_RATE;
        this.agilityRate = DEFAULT_AGILITY_RATE;

        this.element = element;
        this.actions = actions;
        this.condition = StatusCondition.OK;

    }

    /**
     * A copy constructor to copy a monster.
     *
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
        this.precisionRate = monster.precisionRate;

        this.element = monster.element;
        this.actions = monster.actions;
        this.condition = monster.condition;
    }

    /**
     * Returns the name of the monster.
     *
     * @return The name of the monster.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the monster stats(Element, HP, ATK, DEF, SPD).
     *
     * @return Stats of the monster
     */
    public String getStats() {
        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR, this.name + NAME_STATS_SEPARATOR, EMPTY_SUFFIX);

        joiner.add(Element.class.getSimpleName() + STAT_VALUE_SEPARATOR + this.element.name());
        joiner.add(SHORT_HIT_POINTS + STAT_VALUE_SEPARATOR + this.hitPoints);
        joiner.add(Stat.ATK.name() + STAT_VALUE_SEPARATOR + this.attackRate);
        joiner.add(Stat.DEF.name() + STAT_VALUE_SEPARATOR + this.defenceRate);
        joiner.add(Stat.SPD.name() + STAT_VALUE_SEPARATOR + this.speedRate);

        return joiner.toString();
    }

    /**
     * Adds a String value to the monsters name.
     *
     * @param suffix Suffix to be added to name
     */
    public void addNameSuffix(String suffix) {
        this.name += suffix;
    }

    /**
     * Constructs a String that shows the monsters current status during competition.
     *
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

    /**
     * Constructs a string which shows the current monster's actions.
     *
     * @return The string containing all the information
     */
    protected String showActions() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        joiner.add(SHOW_ACTIONS_MONSTER_NAME.formatted(this.name));
        for (Action action : this.actions) {
            joiner.add(action.getInfo());
        }

        return joiner.toString();
    }

    /**
     * Constructs a string showing the current stats of the monster.
     *
     * @return The string containing the information
     */
    protected String showStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(SHOW_STATS_MONSTER_NAME.formatted(this.name)).append(System.lineSeparator());

        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR);
        joiner.add(SHORT_HIT_POINTS + STAT_VALUE_SEPARATOR + this.hitPoints + SHOW_STATS_HEALTH_SEPARATOR
                + this.maxHitPoints);
        joiner.add(Stat.ATK.name() + STAT_VALUE_SEPARATOR + this.attackRate);
        joiner.add(Stat.DEF.name() + STAT_VALUE_SEPARATOR + this.defenceRate);
        joiner.add(Stat.SPD.name() + STAT_VALUE_SEPARATOR + this.speedRate);
        joiner.add(Stat.PRC.name() + STAT_VALUE_SEPARATOR + this.precisionRate);
        joiner.add(Stat.AGL.name() + STAT_VALUE_SEPARATOR + this.agilityRate);

        builder.append(joiner.toString());
        return builder.toString();
    }

    /**
     * Damages the monster.
     * @param damage The damage the monster receives
     */
    public void damage(int damage) {
        if (damage > this.hitPoints) {
            this.hitPoints = HP_NEEDED_FOR_FAINTED;
        } else {
            this.hitPoints -= damage;
        }
    }

    /**
     * Returns the maximum hit points.
     * @return Maximum hit points
     */
    public int getMaxHitPoints() {
        return maxHitPoints;
    }

    /**
     * Returns the element.
     * @return The element
     */
    public Element getElement() {
        return element;
    }

    /**
     * Returns the attack rate.
     * @return The attack rate
     */
    public int getAttackRate() {
        return attackRate;
    }

    /**
     * Returns the defence rate.
     * @return The defence rate
     */
    public int getDefenceRate() {
        return defenceRate;
    }

    /**
     * Returns the speed.
     * @return The speed
     */
    public int getSpeedRate() {
        return speedRate;
    }

    /**
     * Returns the precision rate.
     * @return Precision rate
     */
    public int getPrecisionRate() {
        return precisionRate;
    }

    /**
     * Returns the agility rate.
     * @return Agility rate
     */
    public int getAgilityRate() {
        return agilityRate;
    }

    /**
     * Finds and returns the corresponding action.
     * @param actionName Name of the action
     * @return The found action. Else, returns {@code null}
     */
    protected Action getAction(String actionName) {
        for (Action action : this.actions) {
            if (action.getName().equals(actionName)) {
                return action;
            }
        }
        return null;
    }
}
