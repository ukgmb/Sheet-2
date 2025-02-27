package abschluss.model.effects;

import abschluss.model.Monster;

/**
 * Represents the continue effect. If hit nothing happens.
 *
 * @author ukgmb
 */
public class EffectContinue implements Effect {

    private final int hitRate;

    /**
     * Constructs a new continue effect.
     * @param hitRate Hit rate of the effect
     */
    public EffectContinue(int hitRate) {
        this.hitRate = hitRate;
    }

    @Override
    public boolean executeEffect(Monster... monsters) {
        return true;
    }
}
