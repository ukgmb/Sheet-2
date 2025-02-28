package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.RandomGenerator;

/**
 * Represents the continue effect. If hit nothing happens.
 *
 * @author ukgmb
 */
public class EffectContinue extends Effect {

    private final int hitRate;

    /**
     * Constructs a new continue effect.
     * @param hitRate Hit rate of the effect
     */
    public EffectContinue(int hitRate) {
        this.hitRate = hitRate;
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

    @Override
    public boolean needsOpponent() {
        return false;
    }
}
