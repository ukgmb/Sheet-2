package abschluss.model;

/**
 * This enum represents the three phases during a round in a competition.
 *
 * @author ukgmb
 */
public enum CompetitionPhases {

    /**
     * In this phase, if less than two monsters are alive the competition ends.
     */
    PHASE_0,
    /**
     * In this phase the monsters choose their actions.
     */
    PHASE_1,
    /**
     * In this phase the monsters execute their actions sequentially.
     */
    PHASE_2
}
