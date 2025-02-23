package a1.view;

import a1.model.Effect;
import a1.model.Element;

import java.util.Collection;

/**
 * Represents the configuration command to declare an action. It requires a name, an element and a collection of maximum 4 effects.
 *
 * @author ukgmb
 */
public class ActionConfigCommand implements Command<Configurator> {

    private String name;
    private Element element;
    private Collection<Effect> effects;

    /**
     * Constructs a new command to declare an action.
     * @param name Name of the action
     * @param element Element of the action
     * @param effects Collection of effects which are executed during the action
     */
    public ActionConfigCommand(String name, Element element, Collection<Effect> effects) {
        this.name = name;
        this.element = element;
        this.effects = effects;
    }

    @Override
    public Result execute(Configurator handle) {
        return Result.success();
    }


}
