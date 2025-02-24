package a1.model.effects;

import a1.model.Monster;

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
}
