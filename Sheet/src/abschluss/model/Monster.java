package abschluss.model;

import abschluss.model.effects.ProtectType;
import abschluss.model.effects.Stat;
import java.util.Set;
import java.util.StringJoiner;

/**
 * Represents a monster in {@code Game}. Monsters are equipped with actions to battle each other in competitions.
 *
 * @author ukgmb
 */
public class Monster {
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
    private static final int BASIC_FACTOR_ATK_DEF_SPD = 2;
    private static final int BASIC_FACTOR_PRC_AGL = 3;
    private static final int POSITIVE_CHANGE_CONDITION = 0;
    private static final int NO_CHANGE_FACTOR = 1; //Doesn't change anything if you multiply by one
    private static final double DECREASE_25_FACTOR = 3.0 / 4.0;

    private static final String TAKES_DAMAGE_EFFECT_MSG = "%s takes %s damage!";
    private static final String TAKES_BURN_DAMAGE_MSG = "%s takes %s damage from burning!";
    private static final String TAKES_NO_DAMAGE_MSG = "%s is protected and takes no damage!";
    private static final String GAINS_HEALTH_MSG = "%s gains back %s health!";
    private static final String STAT_RISES_MSG = "%s's %s rises!";
    private static final String STAT_FALLS_MEG = "%s's %s decreases...";
    private static final String TAKES_NO_STAT_CHANGE = "%s is protected and is unaffected!";
    private static final String DIES_MSG = "%s faints!";

    private String name;
    private final int maxHitPoints;
    private int hitPoints;
    private final MonsterStats stats;

    private final Element element;
    private final Set<Action> actions;
    private StatusCondition condition;
    private ProtectType protect;
    private int protectLeft;

    private StringJoiner message;

    /**
     * Constructs a new monster. Declaration of HitPoints, AttackRate, DefenceRate and SpeedRate are required.
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
        this.stats = new MonsterStats(attackRate, defenceRate, speedRate);
        this.element = element;
        this.actions = actions;
        this.condition = StatusCondition.OK;
        this.protect = null;
        this.message = new StringJoiner(System.lineSeparator());
    }

    /**
     * A copy constructor to copy a monster.
     * @param monster The monster to be copied
     */
    public Monster(Monster monster) {
        this.name = monster.name;
        this.maxHitPoints = monster.hitPoints;
        this.hitPoints = monster.hitPoints;
        this.stats = new MonsterStats(monster.stats);
        this.element = monster.element;
        this.actions = monster.actions;
        this.condition = monster.condition;
        this.protect = monster.protect;
        this.protectLeft = monster.protectLeft;
        this.message = monster.message;
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
        joiner.add(SHORT_HIT_POINTS + STAT_VALUE_SEPARATOR + this.hitPoints);
        joiner.add(this.stats.getMainStats());
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
        joiner.add(isFainted() ? CURRENT_CONDITION_FORMAT.formatted(FAINTED_CONDITION)
                : CURRENT_CONDITION_FORMAT.formatted(this.condition.name()));
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

    /**
     * Returns whether monster has fainted.
     * @return {@code true}, if fainted. Else, returns {@code false}
     */
    protected boolean isFainted() {
        return this.hitPoints == HP_NEEDED_FOR_FAINTED;
    }

    /**
     * Constructs a string which shows the current monster's actions.
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
     * @return The string containing the information
     */
    protected String showStats() {
        StringBuilder builder = new StringBuilder();
        builder.append(SHOW_STATS_MONSTER_NAME.formatted(this.name)).append(System.lineSeparator());
        StringJoiner joiner = new StringJoiner(STATS_SEPARATOR);
        joiner.add(SHORT_HIT_POINTS + STAT_VALUE_SEPARATOR + this.hitPoints + SHOW_STATS_HEALTH_SEPARATOR
                + this.maxHitPoints);
        joiner.add(this.stats.getAllStatsWithChange());
        builder.append(joiner);
        return builder.toString();
    }

    /**
     * Damages the monster.
     * @param damage The damage the monster receives
     * @param protectable Whether this damage can be protected or not
     */
    public void damage(int damage, boolean protectable) {
        if (!(this.protect == ProtectType.HEALTH && protectable)) {
            if (damage > this.hitPoints) {
                this.hitPoints = HP_NEEDED_FOR_FAINTED;
            } else {
                this.hitPoints -= damage;
            }
            this.message.add(TAKES_DAMAGE_EFFECT_MSG.formatted(this.name, damage));
            if (this.hitPoints == HP_NEEDED_FOR_FAINTED) {
                this.message.add(DIES_MSG.formatted(this.name));
            }
            return;
        }
        this.message.add(TAKES_NO_DAMAGE_MSG.formatted(this.name));
    }

    /**
     * Heals the monster.
     * @param heal The heal value the monster receives
     */
    public void heal(int heal) {
        if (this.hitPoints + heal > this.maxHitPoints) {
            this.hitPoints = this.maxHitPoints;
        } else {
            this.hitPoints += heal;
        }
        this.message.add(GAINS_HEALTH_MSG.formatted(this.name, heal));
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

    private int getStat(Stat stat) {
        return this.stats.getStat(stat);
    }

    /**
     * Returns the effective stat value by taking status change and status condition into consideration.
     * @param stat The type of stat to be handled.
     * @return The effect value of the stat.
     */
    public double getEffectiveStat(Stat stat) {
        double statFactor = getStatFactor(stat);
        double conditionFactor;
        if (this.condition != StatusCondition.SLEEP && (stat == Stat.ATK || stat == Stat.DEF || stat == Stat.SPD)) {
            conditionFactor = DECREASE_25_FACTOR;
        } else {
            conditionFactor = NO_CHANGE_FACTOR;
        }
        return getStat(stat) * statFactor * conditionFactor;
    }

    private double getStatFactor(Stat stat) {
        double baseFactor;
        if (stat == Stat.ATK || stat == Stat.DEF || stat == Stat.SPD) {
            baseFactor = BASIC_FACTOR_ATK_DEF_SPD;
        } else {
            baseFactor = BASIC_FACTOR_PRC_AGL;
        }
        int changeFactor = getChangeStat(stat);

        double statFactor;
        if (changeFactor >= POSITIVE_CHANGE_CONDITION) {
            statFactor = ((baseFactor + changeFactor) / baseFactor);
        } else {
            statFactor = (baseFactor / (baseFactor - changeFactor));
        }
        return statFactor;
    }

    private int getChangeStat(Stat stat) {
        return this.stats.getChangeStat(stat);
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

    /**
     * Ends the suffering under a status condition.
     */
    protected void endSuffering() {
        this.condition = StatusCondition.OK;
    }

    /**
     * Returns current status condition.
     * @return Current status condition
     */
    protected StatusCondition getCondition() {
        return this.condition;
    }

    /**
     * Inflicts a stat change.
     * @param stat The affected stat
     * @param change Rate of change
     * @param protectable Monster is protected
     */
    public void inflictStatChange(Stat stat, int change, boolean protectable) {
        if (!(this.protect == ProtectType.STATS && protectable)) {
            this.stats.setStat(stat, change);
            if (change > 0) {
                this.message.add(STAT_RISES_MSG.formatted(this.name, stat.name()));
            } else {
                this.message.add(STAT_FALLS_MEG.formatted(this.name, stat.name()));
            }
        }
        this.message.add(TAKES_NO_STAT_CHANGE.formatted(this.name));
    }

    /**
     * Inflicts a status condition, only if monster isn't already suffering from another status condition.
     * @param statusCondition The status condition to be inflicted
     */
    public void inflictStatusCondition(StatusCondition statusCondition) {
        if (this.condition == StatusCondition.OK) {
            this.condition = statusCondition;
            this.message.add(this.name + statusCondition.getMessageHit());
        }
    }

    /**
     * Adds protection to the monster.
     * @param protect Type of protection to be added
     * @param rounds Number of rounds the protection should hold
     */
    public void addProtection(ProtectType protect, int rounds) {
        this.protect = protect;
        this.protectLeft = rounds + 1;
        this.message.add(this.name + protect.getMessageHit());
    }

    /**
     * Removes protection left by one.
     * @return {@code true}, if protection as ended now. Else, returns {@code false}
     */
    public boolean removeProtection() {
        if (this.protectLeft > 0) {
            this.protectLeft--;
            if (this.protectLeft == 0) {
                this.protect = null;
                return true;
            }
        }
        return false;

    }

    /**
     * Returns the stored message and deletes it.
     * @return The stored message as a string.
     */
    protected String getMessage() {
        String message = this.message.toString();
        this.message = new StringJoiner(System.lineSeparator());
        return message;
    }

    /**
     * Effect that burns this monster by removing some of his health.
     * @param damage The damage received by burn
     */
    public void burnDamage(int damage) {
        if (damage > this.hitPoints) {
            this.hitPoints -= damage;
        } else {
            this.hitPoints = HP_NEEDED_FOR_FAINTED;
        }
        this.message.add(TAKES_BURN_DAMAGE_MSG.formatted(this.name, damage));
        if (this.hitPoints == HP_NEEDED_FOR_FAINTED) {
            this.message.add(DIES_MSG.formatted(this.name));
        }
    }
}