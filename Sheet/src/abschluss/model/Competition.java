package abschluss.model;

import java.util.*;

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

    private final List<Monster> allMonsters;
    private Map<Monster, Integer> countOfMonsters;
    private Map<Monster, Integer> maxNumOfMonster;

    private Monster current; //The monster who is currently at turn

    /**
     * Constructs a new competition with given monsters.
     * @param allMonsters List of monsters to participate in the competition
     */
    protected Competition(List<Monster> allMonsters) {
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
}
