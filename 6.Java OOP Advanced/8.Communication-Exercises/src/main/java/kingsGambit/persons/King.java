package kingsGambit.persons;

import kingsGambit.interfaces.Attackable;

public class King extends Person implements Attackable {

    public King(String name) {
        super(name);
    }

    public void attacked(){
        System.out.printf("King %s is under attack!\n",super.getName());
    }

}
