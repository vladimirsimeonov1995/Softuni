package src.models;


import src.interfaces.Attack;
import src.interfaces.Behavior;

public class Blob {

    private String name;
    private long currentHealth;
    private long damage;
    private Behavior behavior;
    private Attack attack;


    private int initialHealth;

    public Blob(String name, int health, int damage, Behavior behavior, Attack attack) {
        this.name = name;
        this.currentHealth = health;
        this.damage = damage;
        this.behavior = behavior;
        this.attack = attack;

        this.initialHealth = health;
    }

    public long getHealth() {
        return this.currentHealth;
    }

    public void setHealth(long health) {
        this.currentHealth = health;

        if (this.currentHealth < 0) {
            this.currentHealth = 0;
        }

        if (this.currentHealth <= this.initialHealth / 2 && !this.behavior.isTriggered()) {
            this.triggerBehavior();
        }
    }

    public long getDamage() {
        return this.damage;
    }

    public void setDamage(long currentDamage) {
        this.damage = currentDamage;
    }

    public String getName() {
        return this.name;
    }

    public Behavior getBehavior() {
        return this.behavior;
    }

    public void attack(Blob target) {

//        this.triggerBehavior();

        this.attack.execute(this,target);
    }

    public void triggerBehavior() {
//        if (this.behavior instanceof Aggressive) {
//            if (this.behavior.isTriggered()) {
//                ((Aggressive) this.behavior).setIsTriggered(true);
//                this.applyAgressiveTriggerEffect();
//            }
//        }

        this.behavior.trigger(this);
    }

    public void makeTurn(){

        if(this.behavior.isTriggered())
            this.behavior.applyRecurrentEffect(this);

    }


    @Override
    public String toString() {
        if (this.getHealth() <= 0) {
            return String.format("Blob %s KILLED\n", this.getName());
        }

        return String.format("Blob %s: %s HP, %s Damage\n", this.getName(), this.getHealth(), this.getDamage());
    }

//    private void attackAffectSource(Blob source) {
//        source.setHealth(source.getHealth() - source.getHealth() / 2);
//    }

    //    public void respond(int damage) {
//        int currentHealth = this.getHealth();
//        currentHealth -= damage;
//        this.setHealth(currentHealth);
//    }

//    private void attackAffectTarget(Blob source, Blob target) {
//        target.respond(source.getDamage() * 2);
//    }

//    private void applyAgressiveTriggerEffect() {
//        this.setDamage(this.getDamage() * 2);
//    }

//    private void applyAgressiveRecurrentEffect() {
//        if (((Aggressive)this.behavior).toDelayRecurrentEffect()) {
//            ((Aggressive)this.behavior).setToDelayRecurrentEffect(false);
//        } else {
//            this.setDamage(this.getDamage() - 5);
//
//            if (this.getDamage() <= this.initialHealth) {
//                this.setDamage(this.initialDamage);
//            }
//        }
//    }

    //    public void update() {
//        if (this.behavior.isTriggered()) {
//            if (this.behavior instanceof Aggressive) {
//                if (this.behavior.isTriggered()) {
//                    ((Aggressive) this.behavior).setIsTriggered(true);
//                    this.applyAgressiveRecurrentEffect();
//                }
//            }
//        }
//    }
}
