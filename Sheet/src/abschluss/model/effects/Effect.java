package abschluss.model.effects;

import abschluss.model.RandomGenerator;

/**
 * Represents an effect in an {@code Action}. Implemented effects can be directly executed using
 * {@link #executeEffect}.
 *
 * @author ukgmb
 */
public abstract class Effect {

    protected ArgumentsEffectExecution arguments;

    /**
     * Constructs a new effect.
     */
    protected Effect() {
        this.arguments = null;
    }

    /**
     * Executes the effect.
     * @param random The random generator used to determine some calculations.
     * @return {@code true}, if effect was hit. Else, returns {@code false}.
     */
    public abstract boolean executeEffect(RandomGenerator random);

    /**
     * Returns the damage the effect does.
     * @return If effect is damage, then return damage. Else, returns {@code null}
     */
    public abstract Strength getDamage();

    /**
     * Returns the hit rate.
     * @return THe hit rate
     */
    public abstract int getHitRate();

    /**
     * Effect needs opponent.
     * @return {@code true}, if needed. Else, returns {@code false}
     */
    public abstract boolean needsOpponent();

    /**
     * Gives the effect the arguments, which are later necessary to execute the effect.
     * @param arguments The arguments needed to execute the effect
     */
    protected void giveArguments(ArgumentsEffectExecution arguments) {
        this.arguments = arguments;
    }
}
