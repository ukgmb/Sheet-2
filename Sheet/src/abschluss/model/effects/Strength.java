package abschluss.model.effects;

/**
 * Describes the strength of the {@link EffectDamage} or {@link EffectHeal}.
 *
 * @author ukgmb
 */
public class Strength {

    private final StrengthType strengthType;
    private final int value;

    /**
     * Constructs a new instance of Strength.
     * @param strengthType Type of strength (see {@link StrengthType})
     * @param value Value of strength, depends on the type of strength
     */
    public Strength(StrengthType strengthType, int value) {
        this.strengthType = strengthType;
        this.value = value;
    }
}
