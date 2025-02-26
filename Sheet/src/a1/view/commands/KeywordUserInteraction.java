package a1.view.commands;

import a1.view.InvalidArgumentException;
import a1.view.Keyword;
import a1.view.Provider;
import a1.view.UserInteraction;

/**
 * This enum represents all the keywords handling {@link UserInteraction}.
 *
 * @author ukgmb
 */
public enum KeywordUserInteraction implements Keyword<Command<UserInteraction>, ArgumentsCommand> {

    /**
     * Represents the {@link CommandQuit} to quit the user interaction and the game.
     */
    QUIT(arguments -> new CommandQuit()),
    /**
     * Represents the {@link CommandLoad} to load a new configuration to the game.
     */
    LOAD(arguments -> new CommandLoad(arguments.parsePath()));

    private final Provider<Command<UserInteraction>, ArgumentsCommand> provider;

    KeywordUserInteraction(Provider<Command<UserInteraction>, ArgumentsCommand> provider) {
        this.provider = provider;
    }

    @Override
    public Command<UserInteraction> provide(ArgumentsCommand arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    @Override
    public boolean matches(String input) {
        return name().toLowerCase().equals(input);
    }
}
