package abschluss.model.effects;

import abschluss.model.RandomGenerator;

import java.util.List;

/**
 * Represents the stat protect effect which either protects the health or the stats of a monster.
 *
 * @author ukgmb
 */
public class EffectStatProtect extends Effect {

    private final ProtectType protect;
    private final Count count;
    private final int hitRate;

    /**
     * Constructs a new stat protect effect.
     * @param protect Type of stat to protect (see {@link ProtectType}
     * @param count Number of the round to be protected
     * @param hitRate Hit rate of the effect
     */
    public EffectStatProtect(ProtectType protect, Count count, int hitRate) {
        super(false);
        this.protect = protect;
        this.count = count;
        this.hitRate = hitRate;
    }

    @Override
    public boolean needsOpponent() {
        return false;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        if (Effect.hit(this.arguments.getUserMonster(), this.arguments.getTargetMonster(), this.hitRate, random)) {
            this.arguments.getUserMonster().addProtection(this.protect, this.count.getCount(random));
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
