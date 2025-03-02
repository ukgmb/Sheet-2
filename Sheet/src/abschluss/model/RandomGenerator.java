package abschluss.model;

import java.util.Random;

/**
 * This class generates pseudorandom numbers for {@link abschluss.model.Game}.
 *
 * @author ukgmb
 */
public class RandomGenerator {

    private static final double PER_CENT_FACTOR = 100;

    private final Random randomGen;

    /**
     * Constructs a new random generator instance.
     * @param randomGen The random generator
     */
    public RandomGenerator(Random randomGen) {
        this.randomGen = randomGen;
    }

    /**
     * Generates random number to determine output of {@code true} or {@code false}.
     * @param percentage Percentage of success
     * @return {@code true}, if hit. Else, returns {@code false}
     */
    public boolean outcomeOf(double percentage) {
        return this.randomGen.nextDouble() * PER_CENT_FACTOR <= percentage;
    }

    /**
     * Generates a random double value.
     * @param min Minimum number
     * @param max Maximum number
     * @return Random generated double
     */
    public double getRandomDouble(double min, double max) {
        return this.randomGen.nextDouble(min, max);
    }

    /**
     * Generates a random integer value.
     * @param min Minimum number
     * @param max Maximum number
     * @return Random generated integer
     */
    public int getRandomInteger(int min, int max) {
        return this.randomGen.nextInt(min, max + 1);
    }
}
