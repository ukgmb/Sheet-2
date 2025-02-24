package a1.model.effects;

import a1.model.Monster;

import java.util.List;

/**
 * Represents the repeat effect which repeats the given effects by a given amount of times.
 *
 * @author ukgmb
 */
public class EffectRepeat implements Effect {

    private final Count count;
    private final List<Effect> effects;

    /**
     * Constructs a new repeat effect.
     * @param count Amount of times the effect should be executed
     * @param effects All the effects except the {@code EffectRepeat} which should be executed.
     */
    public EffectRepeat(Count count, List<Effect> effects) {
        this.count = count;
        this.effects = effects;
    }

    @Override
    public boolean executeEffect(Monster... monsters) {
        return true;
    }
}
