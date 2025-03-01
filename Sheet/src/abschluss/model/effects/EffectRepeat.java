package abschluss.model.effects;

import abschluss.model.RandomGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the repeat effect which repeats the given effects by a given amount of times.
 *
 * @author ukgmb
 */
public class EffectRepeat extends Effect {
    private static final int FIRST_EFFECT_INDEX = 0;

    private final Count count;
    private final List<Effect> effects;

    /**
     * Constructs a new repeat effect.
     * @param count Amount of times the effect should be executed
     * @param effects All the effects except the {@code EffectRepeat} which should be executed.
     */
    public EffectRepeat(Count count, List<Effect> effects) {
        super(true);
        this.count = count;
        this.effects = effects;
    }

    @Override
    public boolean needsOpponent() {
        for (Effect effect : this.effects) {
            if (effect.needsOpponent()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean executeEffect(RandomGenerator random) {
        return false;
    }

    @Override
    public Strength getDamage() {
        return null;
    }

    @Override
    public int getHitRate() {
        return this.effects.get(FIRST_EFFECT_INDEX).getHitRate();
    }

    @Override
    public List<Effect> getEffects() {
        int count = this.count.getCount();
        List<Effect> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.addAll(this.effects);
        }
        return list;
    }
}
