package a1.model.effects;

import a1.model.StatusCondition;
import a1.view.ArgumentsEffect;
import a1.view.EffectProvider;
import a1.view.InvalidArgumentException;

/**
 * Describes the keywords used while configuring to the predefined effects.
 *
 * @author ukgmb
 */
public enum EffectKeyword implements EffectProvider {

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
     * Configures the {@link EffectContinue}.
     */
    CONTINUE(arguments -> new EffectContinue(arguments.parseHitRate()));

    private static final char WORD_SEPARATOR = '_';

    private final EffectProvider provider;

    EffectKeyword(EffectProvider provider) {
        this.provider = provider;
    }

    @Override
    public Effect provide(ArgumentsEffect arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    /**
     * Returns whether the entered String matches the enum value or not.
     * @param input The String input
     * @return {@code true}, if string matches. Else, returns {@code false}
     */
    public boolean matches(String input) {
        StringBuilder enumValue = new StringBuilder(this.name().toLowerCase());
        for (int i = 0; i < enumValue.length() - 1; i++) {
            if (enumValue.charAt(i) == WORD_SEPARATOR) {
                enumValue.setCharAt(i + 1, Character.toUpperCase(enumValue.charAt(i)));
            }
        }
        String keyword = enumValue.toString().replaceAll(Character.toString(WORD_SEPARATOR), "");
        return keyword.equals(input);
    }
}
