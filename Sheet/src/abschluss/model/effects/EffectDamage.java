package abschluss.model.effects;

import abschluss.model.Monster;

/**
 * Represents the damage effect which damages the targeted monster.
 *
 * @author ukgmb
 */
public class EffectDamage implements Effect {

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
    public boolean executeEffect(Monster... monsters) {
        return true;
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
