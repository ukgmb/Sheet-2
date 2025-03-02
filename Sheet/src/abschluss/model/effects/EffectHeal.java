package abschluss.model.effects;

import abschluss.model.Monster;
import abschluss.model.RandomGenerator;

import java.util.List;

/**
 * Represents the heal effect which heals the targeted monster.
 *
 * @author ukgmb
 */
public class EffectHeal extends Effect {

    private static final double PER_CENT_FACTOR = 100;

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
        super(false);
        this.target = target;
        this.strength = strength;
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
            switch (this.strength.getStrengthType()) {

                case ABS: target.heal(this.strength.getValue());
                    break;
                case REL: target.heal(relativeHeal(target));
                    break;
                case BASE: target.heal(baseHeal(target, random));
                default:
            }
            return true;
        }
        return false;
    }

    private int relativeHeal(Monster target) {
        double percentage = (double) this.strength.getValue() / PER_CENT_FACTOR;
        return (int) Math.ceil(percentage * target.getMaxHitPoints());
    }

    private int baseHeal(Monster target, RandomGenerator random) {
        EffectDamage calculator = new EffectDamage(this.target, this.strength, this.hitRate);
        calculator.giveArguments(this.arguments);
        return calculator.baseDamage(target, random);
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
