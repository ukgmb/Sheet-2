package a1.view;

import a1.model.effects.Effect;
import a1.model.Element;

import java.util.ArrayList;

/**
 * Enum represents all keywords for commands handled by {@code Configurator}.
 *
 * @author ukgmb
 */
public enum ConfigKeyword implements Keyword<Configurator> {

    /**
     * Represents the action command keyword in config to declare an action.
     */
    ACTION(arguments ->  new ActionConfigCommand(arguments.hello(), Element.EARTH, new ArrayList<Effect>()), true),
    /**
     * Represents the monster command keyword in config to declare a monster.
     */
    MONSTER(arguments -> new ActionConfigCommand(arguments.hello(), Element.EARTH, new ArrayList<Effect>()), false);


    private final CommandProvider<Configurator> provider;
    private final boolean requiresMoreLines;

    ConfigKeyword(CommandProvider<Configurator> provider, boolean requiresMoreLines) {
        this.provider = provider;
        this.requiresMoreLines = requiresMoreLines;
    }

    @Override
    public Command<Configurator> provide(ArgumentsCommand arguments) throws InvalidArgumentException {
        return provider.provide(arguments);
    }

    @Override
    public boolean matches(String keyword) {
        return name().toLowerCase().equals(keyword);
    }

    /**
     * Returns whether the command requires more input lines or not.
     * @return {@code true}, if more lines are required. Else, returns {@code false}
     */
    public boolean requiresMoreLines() {
        return this.requiresMoreLines;
    }


}
