package abschluss.model.effects;

import abschluss.model.MonsterActionMonster;
import abschluss.model.RandomGenerator;

import java.util.List;

/**
 * Represents an effect in an {@code Action}. Implemented effects can be directly executed using
 * {@link #executeEffect}.
 *
 * @author ukgmb
 */
public abstract class Effect {

    protected MonsterActionMonster arguments;
    protected boolean isRepeat;

    /**
     * Constructs a new effect.
     * @param isRepeat Whether the effect is an repeat effect or not.
     */
    protected Effect(boolean isRepeat) {
        this.arguments = null;
        this.isRepeat = isRepeat;
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
    public void giveArguments(MonsterActionMonster arguments) {
        this.arguments = arguments;
    }

    /**
     * Returns whether its repeat effect or not.
     * @return {@code true}, if its repeat effect. Else, return {@code false}
     */
    public boolean isRepeat() {
        return isRepeat;
    }

    /**
     * If the effect stores a list of effects, it will be returned.
     * @return List of effects, if found. Else, return {@code null}
     */
    public abstract List<Effect> getEffects();
}
