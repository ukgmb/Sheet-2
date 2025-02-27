package abschluss.view.configurator;

import abschluss.model.effects.Count;
import abschluss.model.effects.Effect;
import abschluss.model.effects.Strength;
import abschluss.model.effects.StrengthType;
import abschluss.view.InvalidArgumentException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * This class represents the arguments of an {@link Effect}.
 *
 * @author ukgmb
 */
public class ArgumentsEffect {

    private static final String ERROR_MESSAGE_TOO_FEW_ARGUMENTS = "too few arguments provided.";
    private static final String ERROR_MESSAGE_HIT_RATE = " hit rate must be an integer between 0 and 100"
            + " But provided %s.";
    private static final String ERROR_MESSAGE_STRENGTH = " strength value must be an integer. But provided %s";
    private static final String ERROR_MESSAGE_STRENGTH_VALUE_NEG = "absolute value mustn't be a negative integer."
            + " Provided %s";
    private static final String ERROR_MESSAGE_STRENGTH_VALUE_REL = "relative value must be between 0 and 100"
            + " But provided %s";
    private static final String ERROR_MESSAGE_NEG_RANDOM_COUNT_NUMBER = "random count number must be a non-negative "
            + "integer. But provided %s";
    private static final String ERROR_MESSAGE_NEG_COUNT_NUMBER = "count number must be a non-negative integer."
            + " But provided %s";
    private static final String ERROR_MESSAGE_CHANGE_INTEGER = "value for status change must be an integer"
            + " But provided %s";
    private static final String ERROR_MESSAGE_ENUM_PREFIX = "%s must be ";
    private static final String ERROR_MESSAGE_ENUM_DELIMITER = ", ";
    private static final String ERROR_MESSAGE_ENUM_SUFFIX = ". But provided %s.";
    private static final String ERROR_MESSAGE_RANDOM_INTERVAL_INVALID = "the interval for the random count is invalid."
            + "Min value is higher than max value";

    private static final String DELIMITER_WORD_SEPARATOR = " ";

    private static final int HIT_RATE_MINIMUM = 0;
    private static final int HIT_RATE_MAXIMUM = 100;
    private static final int REL_STRENGTH_MIN = 0;
    private static final int REL_STRENGTH_MAX = 100;
    private static final String RANDOM_KEYWORD_COUNT = "random";

    private final List<String> arguments;

    private final ArgumentsConfiguration argumentsConfiguration;

    /**
     * Constructs a new instance of arguments of effects.
     *
     * @param arguments The provided arguments
     * @param argumentsConfiguration Other arguments outside the effect declaration.
     */
    public ArgumentsEffect(String arguments, ArgumentsConfiguration argumentsConfiguration) {
        String[] split = arguments.split(DELIMITER_WORD_SEPARATOR);
        this.arguments = new LinkedList<>(Arrays.asList(split));
        this.argumentsConfiguration = argumentsConfiguration;
    }

    private String retrieveArgument() throws InvalidArgumentException {
        if (isExhausted()) {
            throw new InvalidArgumentException(ERROR_MESSAGE_TOO_FEW_ARGUMENTS);
        }
        return this.arguments.remove(0);
    }

    /**
     * Returns whether all arguments have been parsed.
     *
     * @return {@code true}, if all arguments have been parsed. Else, returns {@code false}
     */
    public boolean isExhausted() {
        return this.arguments.isEmpty();
    }

    /**
     * Parses the hit rate.
     *
     * @return Hit rate, if found
     * @throws InvalidArgumentException if parsing the hit rate failed
     */
    public int parseHitRate() throws InvalidArgumentException {
        String argument = retrieveArgument();
        int value;
        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(ERROR_MESSAGE_HIT_RATE.formatted(argument));
        }

        if (value >= HIT_RATE_MINIMUM && value <= HIT_RATE_MAXIMUM) {
            return value;
        }
        throw new InvalidArgumentException(ERROR_MESSAGE_HIT_RATE.formatted(argument));

    }

    /**
     * Parses the corresponding enum value of a given enum, if found.
     * @param lowerCase Declares whether argument is expected in lower case or not
     * @param values All values of the corresponding enum
     * @param <T> Describes the enum instance
     * @return The corresponding enum instance, if found
     * @throws InvalidArgumentException if parsing of searched enum value fails.
     */
    public <T extends Enum<T>> T parseEnumValue(boolean lowerCase, T[] values) throws InvalidArgumentException {
        String argument = retrieveArgument();
        for (T value: values) {
            if (lowerCase && value.name().toLowerCase().equals(argument)) {
                return value;
            }
            if (value.name().equals(argument)) {
                return value;
            }
        }

        String errorMessage = buildEnumErrorMessage(argument, values, lowerCase);
        throw new InvalidArgumentException(errorMessage);
    }

    private <T extends Enum<T>> String buildEnumErrorMessage(String argument, T[] values, boolean lowerCase) {
        String enumName = values[0].toString();

        StringJoiner joiner = new StringJoiner(ERROR_MESSAGE_ENUM_DELIMITER,
                ERROR_MESSAGE_ENUM_PREFIX.formatted(enumName), ERROR_MESSAGE_ENUM_SUFFIX.formatted(argument));

        for (T value : values) {
            if (lowerCase) {
                joiner.add(value.name().toLowerCase());
            } else {
                joiner.add(value.name());
            }
        }

        return joiner.toString();
    }

    /**
     * Parses the strength.
     * @return Strength, if found
     * @throws InvalidArgumentException if parsing strength failed
     */
    public Strength parseStrength() throws InvalidArgumentException {
        StrengthType strengthType = parseStrengthType();
        return new Strength(strengthType, parseStrengthValue(strengthType));
    }

    private StrengthType parseStrengthType() throws InvalidArgumentException {

        return parseEnumValue(true, StrengthType.values());
    }

    private int parseStrengthValue(StrengthType strengthType) throws InvalidArgumentException {
        String argument = retrieveArgument();
        int value;
        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(ERROR_MESSAGE_STRENGTH.formatted(argument));
        }
        if ((strengthType == StrengthType.BASE || strengthType == StrengthType.ABS) && value < 0) {
            throw new InvalidArgumentException(ERROR_MESSAGE_STRENGTH_VALUE_NEG.formatted(argument));
        }
        if (value < REL_STRENGTH_MIN || value > REL_STRENGTH_MAX) {
            throw new InvalidArgumentException(ERROR_MESSAGE_STRENGTH_VALUE_REL.formatted(argument));
        }

        return value;
    }

    /**
     * Parses the count instance.
     * @return The count instance
     * @throws InvalidArgumentException if parsing failed
     */
    public Count parseCount() throws InvalidArgumentException {
        String argument = retrieveArgument();

        if (argument.equals(RANDOM_KEYWORD_COUNT)) {
            int min;
            try {
                min = Integer.parseInt(retrieveArgument());
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException(ERROR_MESSAGE_NEG_RANDOM_COUNT_NUMBER.formatted(argument));
            }
            if (min < 0) {
                throw new InvalidArgumentException(ERROR_MESSAGE_NEG_RANDOM_COUNT_NUMBER.formatted(argument));
            }

            int max;
            try {
                max = Integer.parseInt(retrieveArgument());
            } catch (NumberFormatException e) {
                throw new InvalidArgumentException(ERROR_MESSAGE_NEG_RANDOM_COUNT_NUMBER.formatted(argument));
            }
            if (max < 0) {
                throw new InvalidArgumentException(ERROR_MESSAGE_NEG_RANDOM_COUNT_NUMBER.formatted(argument));
            }

            if (min > max) {
                throw new InvalidArgumentException(ERROR_MESSAGE_RANDOM_INTERVAL_INVALID);
            }

            return new Count(min, max);
        }

        int value;
        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(ERROR_MESSAGE_NEG_COUNT_NUMBER.formatted(argument));
        }
        if (value < 0) {
            throw new InvalidArgumentException(ERROR_MESSAGE_NEG_COUNT_NUMBER.formatted(argument));
        }
        return new Count(value);
    }

    /**
     * Parses an integer.
     * @return The integer, if found.
     * @throws InvalidArgumentException if parsing failed
     */
    public int parseChangeInteger() throws InvalidArgumentException {
        String argument = retrieveArgument();

        int value;
        try {
            value = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            throw new InvalidArgumentException(ERROR_MESSAGE_CHANGE_INTEGER.formatted(argument));
        }

        return value;
    }

    /**
     * Parses a list of effects for the repeat effect, if found.
     * @return The list of effects to be repeated
     * @throws InvalidArgumentException if parsing fails
     */
    public List<Effect> parseRepeatEffects() throws InvalidArgumentException {
        return this.argumentsConfiguration.parseRepeatEffects();
    }
}
