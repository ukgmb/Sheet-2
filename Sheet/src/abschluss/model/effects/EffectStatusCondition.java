package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.StatusCondition;

/**
 * Represents the status condition effect which changes the status condition of the targeted monster.
 *
 * @author ukgmb
 */
public class EffectStatusCondition implements Effect {

    private final TargetMonster target;
    private final StatusCondition condition;
    private final int hitRate;

    /**
     * Constructs a new status condition effect.
     * @param target Target monster
     * @param condition Status condition targeted monster should get
     * @param hitRate Hit rate of the effect
     */
    public EffectStatusCondition(TargetMonster target, StatusCondition condition, int hitRate) {
        this.target = target;
        this.condition = condition;
        this.hitRate = hitRate;
    }

    @Override
    public boolean executeEffect(Monster... monsters) {
        return true;
    }
}
