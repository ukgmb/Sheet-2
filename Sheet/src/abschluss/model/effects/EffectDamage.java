package abschluss.model.effects;

import abschluss.model.Element;
import abschluss.model.Monster;
import abschluss.model.RandomGenerator;

import java.util.List;

/**
 * Represents the damage effect which damages the targeted monster.
 *
 * @author ukgmb
 */
public class EffectDamage extends Effect {

    private static final double PER_CENT_FACTOR = 100;
    private static final double ACTION_ELEMENT_EFFECTIVE_FACTOR = 2;
    private static final double ACTION_ELEMENT_NOT_EFFECTIVE_FACTOR = 0.5;
    private static final double NON_ELEMENT_EFFECTIVE_FACTOR = 1;
    private static final double DIRECT_HIT_FACTOR_BASE = 10;
    private static final double NEGATIVE_FAC = -1;
    private static final double DIRECT_HIT_FACTOR = 2;
    private static final double NOT_DIRECT_HIT_FACTOR = 1;
    private static final double SAME_ELEMENT_FACTOR = 1.5;
    private static final double NOT_SAME_ELEMENT_FACTOR = 1;
    private static final double RANDOM_FACTOR_MIN = 0.85;
    private static final double RANDOM_FACTOR_MAX = 1;
    private static final double NORMALIZATION_FACTOR = 1.0 / 3.0;

    private final TargetMonster target;
    private final Strength strength;
    private final int hitRate;

    /**
     * Constructs a new damage effect.
     * @param target Target of the effect
     * @param strength Strength of the damage effect
     * @param hitRate Hit rate of the damage effect
     */
    public EffectDamage(TargetMonster target, Strength strength, int hitRate) {
        super(false);
        this.target = target;
        this.strength = strength;
        this.hitRate = hitRate;
    }

    @Override
    public boolean needsOpponent() {
        return this.target == TargetMonster.TARGET;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        Monster target = this.arguments.getTargetMonster(this.target);

        if (hit(target, random)) {
            switch (this.strength.getStrengthType()) {

                case ABS: target.damage(this.strength.getValue(), this.arguments.getUserMonster() != target);
                    break;
                case REL: target.damage(relativeDamage(target), this.arguments.getUserMonster() != target);
                    break;
                case BASE: target.damage(baseDamage(target, random), this.arguments.getUserMonster() != target);
                default:
            }
            return true;
        }
        return false;
    }

    private boolean hit(Monster target, RandomGenerator random) {
        if (target == this.arguments.getUserMonster()) {
            return random.outcomeOf(this.hitRate * this.arguments.getUserMonster().getEffectiveStat(Stat.PRC));
        }
        return random.outcomeOf(this.hitRate * (this.arguments.getUserMonster().getEffectiveStat(Stat.PRC)
                / target.getEffectiveStat(Stat.AGL)));
    }

    private int relativeDamage(Monster target) {
        double percentage = (double) this.strength.getValue() / PER_CENT_FACTOR;
        return (int) Math.ceil(percentage * target.getMaxHitPoints());
    }

    private int baseDamage(Monster target, RandomGenerator random) {

        double elementFactor = elementFactor(target);
        double statusFactor = (this.arguments.getUserMonster().getEffectiveStat(Stat.ATK) / target.getEffectiveStat(Stat.DEF));
        double directHitFactor = directHitFactor(target, random);
        double sameElementFactor = sameElementFactor();
        double randomFactor = random.getRandomDouble(RANDOM_FACTOR_MIN, RANDOM_FACTOR_MAX);

        double damage = this.strength.getValue() * elementFactor * statusFactor * directHitFactor * sameElementFactor
                * randomFactor * NORMALIZATION_FACTOR;

        return (int) Math.ceil(damage);
    }

    private double elementFactor(Monster target) {
        Element elementAction = this.arguments.getActionElement();
        Element elementTarget = target.getElement();
        Element betterElement = Element.compare(elementAction, elementTarget);

        if (betterElement == elementAction) {
            return ACTION_ELEMENT_EFFECTIVE_FACTOR;
        } else if (betterElement == elementTarget) {
            return ACTION_ELEMENT_NOT_EFFECTIVE_FACTOR;
        }
        return NON_ELEMENT_EFFECTIVE_FACTOR;
    }

    private double directHitFactor(Monster target, RandomGenerator random) {
        double probability = Math.pow(DIRECT_HIT_FACTOR_BASE, NEGATIVE_FAC
                * (this.arguments.getUserMonster().getEffectiveStat(Stat.SPD) / target.getEffectiveStat(Stat.SPD)))
                * PER_CENT_FACTOR;

        if (random.outcomeOf(probability)) {
            return DIRECT_HIT_FACTOR;
        }
        return NOT_DIRECT_HIT_FACTOR;
    }

    private double sameElementFactor() {
        if (this.arguments.getUserMonster().getElement() == this.arguments.getActionElement()) {
            return SAME_ELEMENT_FACTOR;
        }
        return NOT_SAME_ELEMENT_FACTOR;
    }

    /**
     * Returns the strength of the damage effect.
     * @return THe strength of the damage effect
     */
    public Strength getStrength() {
        return strength;
    }

    @Override
    public Strength getDamage() {
        return this.strength;
    }

    @Override
    public int getHitRate() {
        return this.hitRate;
    }

    @Override
    public List<Effect> getEffects() {
        return null;
    }
}
