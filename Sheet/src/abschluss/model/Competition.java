package abschluss.model;

import abschluss.model.effects.*;
import abschluss.view.Result;
import abschluss.view.UserInteraction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringJoiner;

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
    private static final double PROBABILITY_END_STATUS_CONDITION = 1.0 / 3.0;
    private static final int BURN_DAMAGE = 10;
    private static final int BURN_HIT_RATE = 100;
    private static final String MESSAGE_MONSTER_WON_COMPETITION = "%s has no opponents left and wins the competition!";
    private static final String MESSAGE_TIED_COMPETITION = "All monsters have fainted. The competition ends without a winner!";
    private static final String MESSAGE_PROTECTION_ENDED = "%s's protection fades away...";
    private static final String MESSAGE_MONSTER_TURN = System.lineSeparator() + "It's %s's turn.";
    private static final String MESSAGE_MONSTER_PASSES = "%s passes!";
    private static final String MESSAGE_MONSTER_USES_ACTION = "%s uses %s!";
    private static final int NUM_OF_MONSTERS_WIN = 1;

    private final List<Monster> allMonsters;
    private final List<Monster> aliveMonsters;
    private Map<Monster, Integer> countOfMonsters;
    private Map<Monster, Integer> maxNumOfMonster;
    private List<MonsterActionMonster> actionsQueue;

    private Monster current; //The monster who is currently at turn
    private CompetitionPhases phase;
    private final UserInteraction handler;
    private final RandomGenerator random;
    private final Game game;
    private StringJoiner message;

    /**
     * Constructs a new competition with given monsters.
     * @param allMonsters List of monsters to participate in the competition
     * @param handler The user interaction that handle the competition
     * @param random The random generator
     * @param game The game in which the competition is currently played
     */
    protected Competition(List<Monster> allMonsters, UserInteraction handler, RandomGenerator random, Game game) {
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
        this.aliveMonsters = new ArrayList<>(this.allMonsters);
        this.handler = handler;
        this.phase = CompetitionPhases.PHASE_0;
        this.actionsQueue = new ArrayList<>();
        this.random = random;
        this.game = game;
        this.message = new StringJoiner(System.lineSeparator());
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
     * Returns the game instance in which the competition is held.
     * @return The game instance in which the competition is held
     */
    public Game getGame() {
        return game;
    }

    /**
     * Constructs a string which shows every monster's current status with a health bar and status condition.
     * @return The string containing all the information
     */
    public String show() {
        StringJoiner joiner = new StringJoiner(System.lineSeparator());

        for (Monster monster : this.aliveMonsters) {
            String status = monster.getStatus().formatted(this.aliveMonsters.indexOf(monster) + 1,
                    monster == this.current ? MARKING_CURRENT_MONSTER : MARKING_NOT_CURRENT_MONSTER);
            joiner.add(status);
        }

        return joiner.toString();
    }

    /**
     * Shows every action of the current monster in a string.
     * @return The string containing all the information
     */
    public String showActions() {
        return this.current.showActions();
    }

    /**
     * Returns the stats of the current monster.
     * @return The stats of the current monster
     */
    public String showStats() {
        return this.current.showStats();
    }

    private void nextMonstersTurn() {
        int index = (this.allMonsters.indexOf(this.current) + 1) % this.allMonsters.size();
        this.current = this.allMonsters.get(index);
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
        if (this.phase == CompetitionPhases.PHASE_1) {
            this.actionsQueue = new ArrayList<>();
        }
    }

    /**
     * Evaluates the action command.
     * @param action The action the current monster plays
     * @param target The targeted monster of the action
     */
    public void action(Action action, Monster target) {
        this.actionsQueue.add(new MonsterActionMonster(this.current, action, target));

        nextMonstersTurn();
        if (this.allMonsters.get(FIRST_MONSTER_TURN_INDEX) == this.current) {
            nextPhase();
            executePhaseII();
            nextPhase();

        }
    }

    /**
     * Evaluates if someone has won the competition.
     * @return Result success with winner, or {@code null} and competition continues.
     */
    public Result evaluatePhase0() {
        if (this.phase != CompetitionPhases.PHASE_0) {
            return null;
        }
        this.aliveMonsters.removeIf(Monster::isFainted);
        if (this.aliveMonsters.size() > NUM_OF_MONSTERS_WIN) {
            nextPhase();
            return null;
        }
        return this.aliveMonsters.size() == NUM_OF_MONSTERS_WIN
                ? Result.success(MESSAGE_MONSTER_WON_COMPETITION.formatted(this.aliveMonsters.get(FIRST_MONSTER_TURN_INDEX).getName()))
                : Result.success(MESSAGE_TIED_COMPETITION);
    }

    private void executePhaseII() {
        this.actionsQueue.sort(Comparator.comparingInt(MonsterActionMonster::getEffectiveSpeedValue));

        for (MonsterActionMonster entry : this.actionsQueue) {
            this.message.add(MESSAGE_MONSTER_TURN.formatted(entry.getUserMonster().getName()));
            if (entry.getAction() == null) {
                this.message.add(MESSAGE_MONSTER_PASSES.formatted(entry.getUserMonster().getName()));
                continue;
            }
            this.message.add(MESSAGE_MONSTER_USES_ACTION.formatted(entry.getUserMonster().getName(), entry.getAction().getName()));
            Queue<Effect> queue = new ArrayDeque<>();
            List<Effect> actionEffects = entry.getAction().getEffects();
            for (Effect effect : actionEffects) {
                if (effect.isRepeat()) {
                    queue.addAll(effect.getEffects(this.random));
                } else {
                    queue.add(effect);
                }
            }

            evaluateMonsterCondition(entry, queue);
        }
    }

    private void evaluateMonsterCondition(MonsterActionMonster arguments, Queue<Effect> queue) {
        if (arguments.getUserMonster().getCondition() != StatusCondition.OK && this.random.outcomeOf(PROBABILITY_END_STATUS_CONDITION)) {
            String message = arguments.getUserMonster().getCondition().getMessageOver();
            arguments.getUserMonster().endSuffering();
            this.message.add(arguments.getUserMonster().getName() + message);
        } else if (arguments.getUserMonster().getCondition() != StatusCondition.OK) {
            String message = arguments.getUserMonster().getCondition().getMessageStatus();
            this.message.add(arguments.getUserMonster().getName() + message);
        }
        if (arguments.getUserMonster().getCondition() == StatusCondition.SLEEP) {
            queue.clear();
        } else if (arguments.getUserMonster().getCondition() == StatusCondition.BURN) {
            queue.add(new EffectBurn(arguments.getUserMonster()));
        }

        executeEffects(arguments, queue);
    }

    private void executeEffects(MonsterActionMonster arguments, Queue<Effect> queue) {
        StringJoiner message = new StringJoiner(System.lineSeparator());
        if (!queue.isEmpty()) {
            Effect firstEffect = queue.poll();
            firstEffect.giveArguments(arguments);
            if (firstEffect.executeEffect(this.random)) {
                message.add(arguments.getTargetMonster().getMessage());
                while (!queue.isEmpty()) {
                    Effect currentEffect = queue.poll();
                    currentEffect.giveArguments(arguments);
                    currentEffect.executeEffect(this.random);
                    message.add(arguments.getTargetMonster().getMessage());
                }
            }
        }
        this.message.add(message.toString());

        refreshProtection();
    }

    private void refreshProtection() {
        for (Monster monster : this.allMonsters) {
            if (monster.removeProtection()) {
                this.message.add(MESSAGE_PROTECTION_ENDED.formatted(monster.getName()));
            }
        }

        printMessage();
    }

    private void printMessage() {
        this.handler.printMessage(this.message.toString());
        this.message = new StringJoiner(System.lineSeparator());
    }

    /**
     * Finds and returns the corresponding action of the current monster.
     * @param actionName Name of the action
     * @return The found action. Else, returns {@code null}
     */
    protected Action getAction(String actionName) {
        return this.current.getAction(actionName);
    }

    /**
     * Searches for the target monster in the competition.
     * @return Target monster if possible. Else, returns {@code null}
     */
    public Monster searchMonster() {
        if (this.aliveMonsters.size() > 2) {
            return null;
        }
        for (Monster monster : this.aliveMonsters) {
            if (monster != this.current) {
                return monster;
            }
        }
        return null;
    }

    /**
     * Returns current monster playing.
     * @return The current monster playing
     */
    public Monster getCurrent() {
        return current;
    }

    /**
     * Searches for monster with the given name.
     * @param name Name of the monster
     * @return Corresponding monster, if found. Else, returns {@code null}
     */
    public Monster getMonster(String name) {
        for (Monster monster : this.aliveMonsters) {
            if (monster.getName().equals(name)) {
                return monster;
            }
        }
        return null;
    }
}
