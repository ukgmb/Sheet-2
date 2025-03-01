package abschluss.model.effects;

import abschluss.model.RandomGenerator;

import java.util.List;

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
        super(false);
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

    @Override
    public List<Effect> getEffects() {
        return null;
    }
}
