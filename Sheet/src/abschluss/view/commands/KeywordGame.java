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
     * Represents the {@link CommandShow} to show monsters of the game.
     */
    SHOW(arguments -> {
        arguments.retrieveArgument();
        return new CommandShowMonsters(); }, "monsters"),
    /**
     * Represents the {@link CommandCompetition} to start a new competition.
     */
    COMPETITION(arguments -> new CommandCompetition(arguments.parseParticipants()), null);


    private final Provider<Command<Game>, ArgumentsCommand> provider;
    private final String additionalArgument;

    KeywordGame(Provider<Command<Game>, ArgumentsCommand> provider, String additionalArgument) {
        this.provider = provider;
        this.additionalArgument = additionalArgument;
    }

    @Override
    public Command<Game> provide(ArgumentsCommand arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    @Override
    public boolean matches(String input, String additionalArgument) {
        if (this.additionalArgument == null) {
            return name().toLowerCase().equals(input);
        }
        return name().toLowerCase().equals(input) && this.additionalArgument.equals(additionalArgument);
    }
}
