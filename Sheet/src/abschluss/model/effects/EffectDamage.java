package abschluss.model.effects;

import abschluss.model.Element;
import abschluss.model.Monster;

/**
 * Represents the damage effect which damages the targeted monster.
 *
 * @author ukgmb
 */
public class EffectDamage extends Effect {

    private static final int DIVIDER_PER_CENT = 100;
    private static final double ACTION_ELEMENT_EFFECTIVE_FACTOR = 2;
    private static final double ACTION_ELEMENT_NOT_EFFECTIVE_FACTOR = 0.5;
    private static final int NON_ELEMENT_EFFECTIVE_FACTOR = 1;

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
        this.target = target;
        this.strength = strength;
        this.hitRate = hitRate;
    }

    @Override
    public boolean executeEffect() {
        Monster target = this.arguments.getTarget(this.target);

        switch (this.strength.getStrengthType()) {

            case ABS: target.damage(this.strength.getValue());
            break;
            case REL: target.damage(relativeDamage(target));
            break;
            case BASE:
        }
    }

    private int relativeDamage(Monster target) {
        double percentage = (double) this.strength.getValue() / DIVIDER_PER_CENT;
        return (int) Math.ceil(percentage * target.getMaxHitPoints());
    }

    private int baseDamage(Monster target) {
        double damage = this.strength.getValue();
        double elementFactor = elementFactor(target);


        return (int) damage;
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
}
