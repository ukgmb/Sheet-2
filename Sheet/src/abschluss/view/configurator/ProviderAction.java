package abschluss.view.configurator;

import abschluss.model.Action;
import abschluss.view.InvalidArgumentException;
import abschluss.view.Provider;

/**
 * Enum represents all keywords which provide{@link Action}.
 *
 * @author ukgmb
 */
public enum ProviderAction implements Provider<Action, ArgumentsConfiguration> {

    /**
     * Represents the action keyword in config to declare an action.
     */
    ACTION(arguments ->  new Action(arguments.parseName(), arguments.parseElement(), arguments.parseEffects()));


    private final Provider<Action, ArgumentsConfiguration> provider;

    ProviderAction(Provider<Action, ArgumentsConfiguration> provider) {
        this.provider = provider;
    }

    @Override
    public Action provide(ArgumentsConfiguration arguments) throws InvalidArgumentException {
        return provider.provide(arguments);
    }


}
