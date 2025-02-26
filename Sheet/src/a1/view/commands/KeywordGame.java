package a1.view.commands;

import a1.model.Game;
import a1.view.InvalidArgumentException;
import a1.view.Keyword;
import a1.view.Provider;
import a1.view.UserInteraction;

/**
 * This enum represents all the keywords handling {@link Game}.
 *
 * @author ukgmb
 */
public enum KeywordGame implements Keyword<Command<Game>, ArgumentsCommand> {


    /**
     * Represents the {@link CommandShow} to show specific information of the game.
     */
    SHOW(arguments -> new CommandShow(arguments.parseParameter()));


    private final Provider<Command<Game>, ArgumentsCommand> provider;

    KeywordGame(Provider<Command<Game>, ArgumentsCommand> provider) {
        this.provider = provider;
    }

    @Override
    public Command<Game> provide(ArgumentsCommand arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    @Override
    public boolean matches(String input) {
        return name().toLowerCase().equals(input);
    }
}
