package abschluss.view.commands;

import abschluss.model.Game;
import abschluss.model.Monster;
import abschluss.view.Result;

import java.util.List;

/**
 * Represents the command to start a new competition. It requires a list of monsters that participate in the competition.
 *
 * @author ukgmb
 */
public class CommandCompetition implements Command<Game> {

    private static final String COMPETITION_START_MESS = "The %s monsters enter the competition.";

    private final List<Monster> participants;

    /**
     * Constructs a new competition command.
     * @param participants List of monsters that participate
     */
    protected CommandCompetition(List<Monster> participants) {
        this.participants = participants;
    }

    @Override
    public Result execute(Game handle) {
        handle.startNewCompetition(this.participants);
        return Result.success(COMPETITION_START_MESS.formatted(this.participants.size()));
    }
}

