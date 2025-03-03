package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.RandomGenerator;

import java.util.List;

/**
 * Represents the damage effect of the burn.
 *
 * @author ukgmb
 */
public class EffectBurn extends Effect {

    private static final double BURN_FACTOR = 0.1;

    private final Monster target;

    /**
     * Constructs a new burn effect.
     * @param target The monster to be burned.
     */
    public EffectBurn(Monster target) {
        super(false);
        this.target = target;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        int damage = (int) Math.ceil(this.target.getMaxHitPoints() * BURN_FACTOR);
        this.target.burnDamage(damage);
        return true;
    }

    @Override
    public Strength getDamage() {
        return null;
    }

    @Override
    public int getHitRate() {
        return 100;
    }

    @Override
    public boolean needsOpponent() {
        return false;
    }

    @Override
    public List<Effect> getEffects(RandomGenerator random) {
        return List.of();
    }
}
