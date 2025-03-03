package abschluss.model;

import abschluss.model.effects.Stat;
import abschluss.model.effects.TargetMonster;

/**
 * This class stores which monster wants to play which action against which monster. Used by {@link Competition}
 *
 * @author ukgmb
 */
public class MonsterActionMonster {

    private final Monster user;
    private final Action action;
    private final Monster target;

    /**
     * Constructs a new instance of this class.
     * @param user Monster playing the action
     * @param action The action to be played
     * @param target The target monster
     */
    protected MonsterActionMonster(Monster user, Action action, Monster target) {
        this.user = user;
        this.action = action;
        this.target = target;
    }

    /**
     * Returns the effective speed rate of the user monster.
     * @return Effective speed rate of the user monster.
     */
    protected int getEffectiveSpeedValue() {
        return (int) Math.ceil(this.user.getEffectiveStat(Stat.SPD));
    }

    /**
     * Returns the target monster depending on the declaration of the effect.
     * @return Target monster
     */
    public Monster getTargetMonster() {
        return this.target;
    }

    /**
     * Returns the element of the action.
     * @return The element of the action.
     */
    public Element getActionElement() {
        return this.action.getElement();
    }

    /**
     * Returns user Monster.
     * @return The user monster
     */
    public Monster getUserMonster() {
        return user;
    }

    /**
     * Returns the action.
     * @return The action
     */
    protected Action getAction() {
        return action;
    }
}
