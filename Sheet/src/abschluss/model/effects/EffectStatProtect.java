package abschluss.model.effects;

import abschluss.model.Monster;

/**
 * Represents the stat protect effect which either protects the health or the stats of a monster.
 *
 * @author ukgmb
 */
public class EffectStatProtect implements Effect {

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
        this.protect = protect;
        this.count = count;
        this.hitRate = hitRate;
    }

    @Override
    public boolean executeEffect(Monster... monsters) {
        return true;
    }
}
