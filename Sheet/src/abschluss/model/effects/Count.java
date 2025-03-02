package abschluss.model.effects;

import abschluss.model.RandomGenerator;

/**
 * This class stores how something is counted. Either count by absolute number or random.
 *
 * @author ukgmb
 */
public class Count {

    private static final int NOT_INITIALIZED_VALUE = -1;

    private final int count;
    private final int min;
    private final int max;

    /**
     * Constructs a new counter with absolute number.
     * @param count Absolute number
     */
    public Count(int count) {
        this.count = count;
        this.min = NOT_INITIALIZED_VALUE;
        this.max = NOT_INITIALIZED_VALUE;
    }

    /**
     * Constructs a new counter which is random.
     * @param min Minimum number
     * @param max Maximum number
     */
    public Count(int min, int max) {
        this.count = NOT_INITIALIZED_VALUE;
        this.min = min;
        this.max = max;
    }

    /**
     * Returns the count number depending on the initialization.
     * @param random The random generator to generate random numbers
     * @return The count
     */
    public int getCount(RandomGenerator random) {
        if (this.count == NOT_INITIALIZED_VALUE) {
            return random.getRandomInteger(this.min, this.max + 1);
        }
        return this.count;
    }


}
