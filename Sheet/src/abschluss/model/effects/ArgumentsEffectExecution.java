package abschluss.model.effects;

import abschluss.model.Action;
import abschluss.model.Element;
import abschluss.model.Monster;

/**
 * This class holds the arguments to execute a {@link Effect}.
 *
 * @author ukgmb
 */
public class ArgumentsEffectExecution {

    private final Monster user;
    private final Monster target;
    private final Action action;

    /**
     * Constructs a new instance of arguments to execute a  {@link Effect}.
     * @param user The monster performing the action
     * @param target The monster that should get hit by effect
     * @param action The action that the user monster performs
     */
    public ArgumentsEffectExecution(Monster user, Monster target, Action action) {
        this.user = user;
        this.target = target;
        this.action = action;
    }

    /**
     * Returns the target monster depending on the declaration of the effect.
     * @param target Type of target expected (see {@link TargetMonster}
     * @return Target monster
     */
    protected Monster getTargetMonster(TargetMonster target) {
        if (target == TargetMonster.TARGET) {
            return this.target;
        }
        return this.user;
    }

    /**
     * Returns the element of the action.
     * @return The element of the action.
     */
    protected Element getActionElement() {
        return this.action.getElement();
    }

    /**
     * Returns the user monster.
     * @return User monster
     */
    public Monster getUserMonster() {
        return user;
    }
}
