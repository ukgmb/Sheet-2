package abschluss.view.configurator;

import abschluss.model.StatusCondition;
import abschluss.model.effects.Effect;
import abschluss.model.effects.EffectContinue;
import abschluss.model.effects.EffectDamage;
import abschluss.model.effects.EffectHeal;
import abschluss.model.effects.EffectRepeat;
import abschluss.model.effects.EffectStatChange;
import abschluss.model.effects.EffectStatProtect;
import abschluss.model.effects.EffectStatusCondition;
import abschluss.model.effects.ProtectType;
import abschluss.model.effects.Stat;
import abschluss.model.effects.TargetMonster;
import abschluss.view.InvalidArgumentException;
import abschluss.view.Keyword;
import abschluss.view.Provider;

/**
 * Describes the keywords used while configuring to the predefined effects.
 *
 * @author ukgmb
 */
public enum KeywordEffect implements Keyword<Effect, ArgumentsEffect> {

    /**
     * Configures the {@link EffectDamage}.
     */
    DAMAGE(arguments -> new EffectDamage(arguments.parseEnumValue(true, TargetMonster.values()),
            arguments.parseStrength(), arguments.parseHitRate())),
    /**
     * Configures the {@link EffectStatusCondition}.
     */
    INFLICT_STATUS_CONDITION(arguments -> new EffectStatusCondition(arguments.parseEnumValue(true,
            TargetMonster.values()), arguments.parseEnumValue(false, StatusCondition.values()),
            arguments.parseHitRate())),
    /**
     * Configures the {@link EffectStatChange}.
     */
    INFLICT_STAT_CHANGE(arguments -> new EffectStatChange(arguments.parseEnumValue(true,
            TargetMonster.values()), arguments.parseEnumValue(false, Stat.values()),
            arguments.parseChangeInteger(), arguments.parseHitRate())),
    /**
     * Configures the {@link EffectStatProtect}.
     */
    PROTECT_STAT(arguments -> new EffectStatProtect(arguments.parseEnumValue(true, ProtectType.values()),
            arguments.parseCount(), arguments.parseHitRate())),
    /**
     * Configures the {@link EffectHeal}.
     */
    HEAL(arguments -> new EffectHeal(arguments.parseEnumValue(true, TargetMonster.values()),
            arguments.parseStrength(), arguments.parseHitRate())),
    /**
     * Configures the {@link EffectRepeat}.
     */
    REPEAT(arguments -> new EffectRepeat(arguments.parseCount(), arguments.parseRepeatEffects())),
    /**
     * Configures the {@link EffectContinue}.
     */
    CONTINUE(arguments -> new EffectContinue(arguments.parseHitRate()));

    private static final char WORD_SEPARATOR = '_';

    private final Provider<Effect, ArgumentsEffect> provider;

    KeywordEffect(Provider<Effect, ArgumentsEffect> provider) {
        this.provider = provider;
    }

    @Override
    public Effect provide(ArgumentsEffect arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    @Override
    public boolean matches(String input, String additionalArgument) {
        StringBuilder enumValue = new StringBuilder(this.name().toLowerCase());
        for (int i = 0; i < enumValue.length() - 1; i++) {
            if (enumValue.charAt(i) == WORD_SEPARATOR) {
                enumValue.setCharAt(i + 1, Character.toUpperCase(enumValue.charAt(i + 1)));
            }
        }
        String keyword = enumValue.toString().replaceAll(Character.toString(WORD_SEPARATOR), "");
        return keyword.equals(input);
    }
}
