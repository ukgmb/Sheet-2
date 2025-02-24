package a1.model.effects;

import a1.model.Monster;

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
}
