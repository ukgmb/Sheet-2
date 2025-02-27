package abschluss.model;

import abschluss.model.effects.Effect;


import java.util.List;

/**
 * Represents an action which can be used by {@code Monster}.
 *
 * @author ukgmb
 */
public class Action {

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
}
