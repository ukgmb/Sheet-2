package abschluss.view.commands;

import abschluss.model.Action;
import abschluss.model.Competition;
import abschluss.view.InvalidArgumentException;
import abschluss.view.Keyword;
import abschluss.view.Provider;

/**
 * This enum represents all the keywords handling {@link Competition}.
 *
 * @author ukgmb
 */
public enum KeywordCompetition implements Keyword<Command<Competition>, ArgumentsCommand> {

    /**
     * Represents the {@link CommandShow} to show details during competition.
     */
    SHOW(arguments -> new CommandShow(arguments.parseParameter())),
    /**
     * Represents the {@link CommandAction} to instructs the current monster in competition to play an action.
     */
    ACTION(arguments -> {
        Action action = arguments.parseAction();
        return new CommandAction(action, arguments.parseTargetMonster(action)); }),
    /**
     * Represents the {@link CommandPass} to pass the current monster's turn.
     */
    PASS(arguments -> new CommandPass());

    private final Provider<Command<Competition>, ArgumentsCommand> provider;

    KeywordCompetition(Provider<Command<Competition>, ArgumentsCommand> provider) {
        this.provider = provider;
    }

    @Override
    public Command<Competition> provide(ArgumentsCommand arguments) throws InvalidArgumentException {
        return this.provider.provide(arguments);
    }

    @Override
    public boolean matches(String input, String additionalArguments) {
        return name().toLowerCase().equals(input);
    }
}
