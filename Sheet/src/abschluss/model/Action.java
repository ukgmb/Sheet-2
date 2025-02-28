package abschluss.model;

import abschluss.model.effects.Effect;
import abschluss.model.effects.Strength;


import java.util.List;
import java.util.StringJoiner;

/**
 * Represents an action which can be used by {@code Monster}.
 *
 * @author ukgmb
 */
public class Action {

    private static final String INFO_PREFIX = "%s: ";
    private static final String INFO_SUFFIX = "";
    private static final String INFO_DELIMITER = ", ";
    private static final String INFO_ELEMENT = "ELEMENT %s";
    private static final String INFO_FIRST_DAMAGE = "Damage %s";
    private static final String INFO_FIRST_HIT_RATE = "HitRate %s";
    private static final String INFO_NO_DAMAGE = "--";
    private static final int FIRST_EFFECT_INDEX = 0;

    private final String name;
    private final Element element;
    private final List<Effect> effects;

    /**
     * Constructs a new action.
     * @param name Name of the action
     * @param element Element of the action
     * @param effects A set of effects for the action
     */
    public Action(String name, Element element, List<Effect> effects) {
        this.name = name;
        this.element = element;
        this.effects = effects;
    }

    /**
     * Returns the name of the action.
     * @return Name of the action.
     */
    public String getName() {
        return name;
    }

    /**
     * Constructs a string containing basic information about the action like name, element, damage (if possible) and
     * hit rate.
     * @return String containing the information
     */
    protected String getInfo() {
        StringJoiner joiner = new StringJoiner(INFO_DELIMITER, INFO_PREFIX.formatted(this.name), INFO_SUFFIX);

        joiner.add(INFO_ELEMENT.formatted(this.element));
        joiner.add(INFO_FIRST_DAMAGE.formatted(getDamageInfo()));
        joiner.add(INFO_FIRST_HIT_RATE.formatted(getHitRateInfo()));

        return joiner.toString();
    }

    private String getDamageInfo() {
        for (Effect effect : this.effects) {
            Strength strength = effect.getDamage();
            if (strength != null) {
                return strength.toString();
            }
        }
        return INFO_NO_DAMAGE;
    }

    private String getHitRateInfo() {
        return Integer.toString(this.effects.get(FIRST_EFFECT_INDEX).getHitRate());
    }

    /**
     * Returns the element of the action.
     * @return The element of the action.
     */
    public Element getElement() {
        return element;
    }
}
