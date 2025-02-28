package abschluss.model.effects;

/**
 * Describes the strength of the {@link EffectDamage} or {@link EffectHeal}.
 *
 * @author ukgmb
 */
public class Strength {

    private static final String BASE_REPRESENTATION = "b";
    private static final String ABSOLUTE_REPRESENTATION = "a";
    private static final String RELATIVE_REPRESENTATION = "r";

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

    @Override
    public String toString() {
        return switch (this.strengthType) {
            case ABS -> ABSOLUTE_REPRESENTATION + this.value;
            case REL -> RELATIVE_REPRESENTATION + this.value;
            case BASE -> BASE_REPRESENTATION + this.value;
        };
    }
}
