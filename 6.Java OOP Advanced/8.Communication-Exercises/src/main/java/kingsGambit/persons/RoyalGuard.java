package kingsGambit.persons;

import kingsGambit.interfaces.Attackable;
import kingsGambit.interfaces.Deadable;
import kingsGambit.persons.Person;

public class RoyalGuard extends Person implements Deadable,Attackable {

    private boolean isDead;

    private int hittedCount;

    public RoyalGuard(String name) {
        super(name);
        this.isDead = false;
        this.hittedCount = 0;
    }




    @Override
    public boolean isDead() {
        return this.isDead;
    }

    @Override
    public void kill() {

        if(this.hittedCount++ == 2) {
            this.isDead = true;
        }
    }

    @Override
    public void attacked() {
        if(!isDead){
            System.out.printf("Royal Guard %s is defending!\n",super.getName());
        }
    }
}
