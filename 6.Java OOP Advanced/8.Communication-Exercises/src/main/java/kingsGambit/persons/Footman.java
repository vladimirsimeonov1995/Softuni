package kingsGambit.persons;

import kingsGambit.interfaces.Attackable;
import kingsGambit.interfaces.Deadable;

public class Footman extends Person implements Deadable, Attackable {

    private boolean isDead;
    private boolean isHittedOnce;

    public Footman(String name) {
        super(name);
        this.isDead = false;
        this.isHittedOnce = false;
    }

    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void kill() {

        if(isHittedOnce) {
            this.isDead = true;
        }

        if(!isHittedOnce){
            isHittedOnce = true;
        }

    }

    @Override
    public void attacked() {
        if(!isDead){
            System.out.printf("Footman %s is panicking!\n",super.getName());
        }
    }
}
