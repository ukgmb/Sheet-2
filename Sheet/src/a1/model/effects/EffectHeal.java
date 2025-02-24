package a1.model.effects;

import a1.model.Monster;

/**
 * Represents the heal effect which heals the targeted monster.
 */
public class EffectHeal implements Effect {

    private final TargetMonster target;
    private final Strength strength;
    private final int hitRate;

    /**
     * Constructs a new damage effect.
     * @param target Target of the effect
     * @param strength Strength of the heal effect
     * @param hitRate Hit rate of the heal effect
     */
    public EffectHeal(TargetMonster target, Strength strength, int hitRate) {
        this.target = target;
        this.strength = strength;
        this.hitRate = hitRate;
    }

    @Override
    public boolean executeEffect(Monster... monsters) {
        return true;
    }
}
