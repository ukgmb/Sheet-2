package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.RandomGenerator;

/**
 * Represents the stat change effect which changes one stat of the target monster.
 *
 * @author ukgmb
 */
public class EffectStatChange extends Effect {

    private final TargetMonster target;
    private final Stat stat;
    private final int change;
    private final int hitRate;

    /**
     * Constructs a new stat change effect.
     * @param target Targeted monster
     * @param stat The stat to be changed
     * @param change Rate of change
     * @param hitRate Hit rate of the effect
     */
    public EffectStatChange(TargetMonster target, Stat stat, int change, int hitRate) {
        this.target = target;
        this.stat = stat;
        this.change = change;
        this.hitRate = hitRate;
    }

    @Override
    public boolean needsOpponent() {
        return this.target == TargetMonster.TARGET;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        return true;
    }

    @Override
    public Strength getDamage() {
        return null;
    }

    @Override
    public int getHitRate() {
        return this.hitRate;
    }
}
