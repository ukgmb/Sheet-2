package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.RandomGenerator;
import abschluss.model.StatusCondition;

import java.util.List;

/**
 * Represents the status condition effect which changes the status condition of the targeted monster.
 *
 * @author ukgmb
 */
public class EffectStatusCondition extends Effect {

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
        super(false);
        this.target = target;
        this.condition = condition;
        this.hitRate = hitRate;
    }

    @Override
    public boolean needsOpponent() {
        return this.target == TargetMonster.TARGET;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        Monster target = this.arguments.getTargetMonster(this.target);

        if (Effect.hit(this.arguments.getUserMonster(), target, this.hitRate, random)) {
            target.inflictStatusCondition(this.condition);
            return true;
        }
        return false;
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
    public List<Effect> getEffects(RandomGenerator random) {
        return null;
    }
}
