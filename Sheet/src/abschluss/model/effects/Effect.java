package abschluss.model.effects;

import abschluss.model.Monster;

/**
 * Represents an effect in an {@code Action}. Implemented effects can be directly executed using
 * {@link #executeEffect(Monster...)}.
 *
 * @author ukgmb
 */
public interface Effect {

    /**
     * Executes the effect.
     * @param monsters The monsters effected
     * @return {@code true}, if effect was hit. Else, returns {@code false}.
     */
    boolean executeEffect(Monster... monsters);

    /**
     * Returns the damage the effect does.
     * @return If effect is damage, then return damage. Else, returns {@code null}
     */
    Strength getDamage();

    /**
     * Returns the hit rate.
     * @return THe hit rate
     */
    int getHitRate();
}
