package a1.view;

import a1.model.Effect;
import a1.model.Element;

import java.util.ArrayList;

/**
 * Enum represents all keywords for commands handled by {@code Configurator}.
 *
 * @author ukgmb
 */
public enum ConfigKeywords implements Keyword<Configurator> {

    /**
     * Represents the action command keyword in config to declare an action.
     */
    ACTION(arguments ->  new ActionConfigCommand(arguments.hello(), Element.EARTH, new ArrayList<Effect>()), true);

    private final CommandProvider<Configurator> provider;
    private boolean requiresMoreLines;

    ConfigKeywords(CommandProvider<Configurator> provider, boolean requiresMoreLines) {
        this.provider = provider;
        this.requiresMoreLines = requiresMoreLines;
    }

    @Override
    public Command<Configurator> provide(Arguments arguments) throws InvalidArgumentException{
        return provider.provide(arguments);
    }

    @Override
    public boolean matches(String keyword) {
        return name().toLowerCase().equals(keyword);
    }


}
