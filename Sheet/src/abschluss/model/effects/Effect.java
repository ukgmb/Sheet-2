package abschluss.model.effects;

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
     * @return {@code true}, if effect was hit. Else, returns {@code false}.
     */
    public abstract boolean executeEffect();

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
     * Gives the effect the arguments, which are later necessary to execute the effect.
     * @param arguments The arguments needed to execute the effect
     */
    protected void giveArguments(ArgumentsEffectExecution arguments) {
        this.arguments = arguments;
    }
}
