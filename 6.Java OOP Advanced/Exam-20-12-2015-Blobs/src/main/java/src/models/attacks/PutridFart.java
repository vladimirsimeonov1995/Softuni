package src.models.attacks;

import src.interfaces.Attack;
import src.models.Blob;

public class PutridFart implements Attack {

    public void execute(Blob source, Blob target) {
//        target.respond(source.getDamage());

        long currentHealth = target.getHealth();
        currentHealth -= source.getDamage();
        target.setHealth(currentHealth);
    }
}
