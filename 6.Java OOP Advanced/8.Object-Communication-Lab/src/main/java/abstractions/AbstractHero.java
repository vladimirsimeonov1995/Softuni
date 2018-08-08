package abstractions;

import enums.LogType;
import interfaces.Attacker;
import interfaces.Handler;
import interfaces.Target;

public abstract class AbstractHero implements Attacker {

    private static final String TARGET_NULL_MESSAGE = "interfaces.Target null";
    private static final String NO_TARGET_MESSAGE = "%s has no target";
    private static final String TARGET_DEAD_MESSAGE = "%s is dead";
    private static final String SET_TARGET_MESSAGE = "%s targets %s";


    private String id;
    private int dmg;
    private Target target;
    private Handler handler;

    protected AbstractHero(String id, int dmg, Handler handler) {
        this.id = id;
        this.dmg = dmg;
        this.handler = handler;
    }

    public void setTarget(Target target) {
        if (target == null) {
            handler.handle(LogType.ERROR,TARGET_NULL_MESSAGE);
        } else {
            this.target = target;
            handler.handle(LogType.TARGET,String.format(SET_TARGET_MESSAGE, this, target));
        }
    }

    public Handler getHandler() {
        return this.handler;
    }

    public final void attack() {
        if (this.target == null) {
            handler.handle(LogType.ERROR,String.format(NO_TARGET_MESSAGE, this));
        } else if (this.target.isDead()) {
            handler.handle(LogType.ERROR,String.format(TARGET_DEAD_MESSAGE, target));
        } else {
            this.executeClassSpecificAttack(this.target, this.dmg);
        }
    }



    @Override
    public String toString() {
        return this.id;
    }



    protected abstract void executeClassSpecificAttack(Target target, int dmg);
}
