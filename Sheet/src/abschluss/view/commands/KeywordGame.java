package abschluss.view.commands;

import abschluss.model.Game;
import abschluss.view.InvalidArgumentException;
import abschluss.view.Keyword;
import abschluss.view.Provider;

/**
 * This enum represents all the keywords handling {@link Game}.
 *
 * @author ukgmb
 */
public enum KeywordGame implements Keyword<Command<Game>, ArgumentsCommand> {


    /**
     * Represents the {@link CommandShow} to show specific information of the game.
     */
    SHOW(arguments -> new CommandShow(arguments.parseParameter())),
    /**
     * Represents the {@link CommandCompetition} to start a new competition.
     */
    COMPETITION(arguments -> new CommandCompetition(arguments.parseParticipants()));


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
