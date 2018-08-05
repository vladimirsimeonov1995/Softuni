package src.models.attacks;

import src.interfaces.Attack;
import src.models.Blob;

public class Blobplode implements Attack {


    public void execute(Blob attacker, Blob target) {

        long attackerNewHealth = attacker.getHealth() - (attacker.getHealth() / 2);
        attacker.setHealth(attackerNewHealth);

        long currentHealth = target.getHealth();
        currentHealth -= attacker.getDamage() * 2;
        target.setHealth(currentHealth);

    }
}
