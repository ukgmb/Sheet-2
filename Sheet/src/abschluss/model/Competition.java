package abschluss.model;

import abschluss.view.UserInteraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class handles a competition between {@link Monster}.
 *
 * @author ukgmb
 */
public class Competition {

    private static final String PREFIX_SAME_MONSTER = "#";
    private static final int FIRST_MONSTER_TURN_INDEX = 0;
    private static final String MARKING_CURRENT_MONSTER = "*";
    private static final String MARKING_NOT_CURRENT_MONSTER = "";
    private static final String MONSTER_TURN_DEMANDS_ACTION = "What should %s do ?";

    private final List<Monster> allMonsters;
    private Map<Monster, Integer> countOfMonsters;
    private Map<Monster, Integer> maxNumOfMonster;

    private Monster current; //The monster who is currently at turn
    private CompetitionPhases phase;
    private final UserInteraction handler;

    /**
     * Constructs a new competition with given monsters.
     * @param allMonsters List of monsters to participate in the competition
     */
    protected Competition(List<Monster> allMonsters, UserInteraction handler) {
        countMonsters(allMonsters);
        this.allMonsters = new ArrayList<>();
        for (Monster monster : allMonsters) {
            if (this.countOfMonsters.containsKey(monster)) {
                Monster duplicateMonster = new Monster(monster);
                duplicateMonster.addNameSuffix(PREFIX_SAME_MONSTER + getMonsterNumber(monster));
                this.allMonsters.add(duplicateMonster);
            } else {
                this.allMonsters.add(new Monster(monster));
            }
        }
        this.current = this.allMonsters.get(FIRST_MONSTER_TURN_INDEX);
        this.handler = handler;
        this.phase = CompetitionPhases.PHASE_1;
    }

    private void countMonsters(List<Monster> monstersAlive) {
        this.countOfMonsters = new HashMap<>();
        for (Monster monster : monstersAlive) {
            this.countOfMonsters.merge(monster, 1, Integer::sum);
        }

        this.countOfMonsters.keySet().removeIf(monster -> this.countOfMonsters.get(monster) == 1);

        this.maxNumOfMonster = Map.copyOf(this.countOfMonsters);
    }

    private int getMonsterNumber(Monster monster) {
        int value =  this.maxNumOfMonster.get(monster) - this.countOfMonsters.get(monster) + 1;
        this.countOfMonsters.merge(monster, -1, Integer::sum);
        return value;
    }

    /**
     * Constructs a string which shows every monster's current status with a health bar and status condition.
     * @return The string containing all the information
     */
    protected String show() {
        StringBuilder builder = new StringBuilder();

        for (Monster monster : this.allMonsters) {
            String status = monster.getStatus().formatted(this.allMonsters.indexOf(monster) + 1,
                    monster == this.current ? MARKING_CURRENT_MONSTER : MARKING_NOT_CURRENT_MONSTER);
            builder.append(status).append(System.lineSeparator());
        }

        return builder.toString();
    }

    /**
     * Shows every action of the current monster in a string.
     * @return The string containing all the information
     */
    protected String showActions() {
        return this.current.showActions();
    }

    /**
     * Returns the stats of the current monster.
     * @return The stats of the current monster
     */
    protected String showStats() {
        return this.current.showStats();
    }

    /**
     * Next monster in the list is at turn in a competition.
     * @return always returns true
     */
    protected boolean nextMonstersTurn() {
        int index = (this.allMonsters.indexOf(this.current) + 1) % this.allMonsters.size();
        this.current = this.allMonsters.get(index);
        return true;
    }

    /**
     * If competition is in phase 1 of a round. Message to the user is given to enter the action.
     * @return The message
     */
    protected String whatMonsterShouldDo() {
        if (this.phase == CompetitionPhases.PHASE_1) {
            return MONSTER_TURN_DEMANDS_ACTION.formatted(this.current.getName());
        }
        return null;
    }

    private void nextPhase() {
        int ordinal = (this.phase.ordinal() + 1) % CompetitionPhases.values().length;
        this.phase = CompetitionPhases.values()[ordinal];
    }

    /**
     * Evaluates the action command.
     */
    protected void action() {
        // Queses action

        nextMonstersTurn();
        if (this.allMonsters.get(0) == this.current) {
            nextPhase();
        }
    }

    private void executePhaseII() {

    }

    /**
     * Finds and returns the corresponding action of the current monster.
     * @param actionName Name of the action
     * @return The found action. Else, returns {@code null}
     */
    protected Action getAction(String actionName) {
        return this.current.getAction(actionName);
    }
}
