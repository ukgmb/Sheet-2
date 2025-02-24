package a1.view;

import a1.model.effects.Effect;

/**
 * This interface provides an effect instance constructed with the given arguments.
 *
 * @author ukgmb
 */
public interface EffectProvider {

    /**
     * Constructs a new effect instance with the given arguments.
     * @param arguments the arguments to be used for constructing the command
     * @return the constructed effect instance
     * @throws InvalidArgumentException if parsing/retrieving an argument fails
     */
    Effect provide(ArgumentsEffect arguments) throws InvalidArgumentException;
}
