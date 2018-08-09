package kingsGambit.persons;

import kingsGambit.interfaces.Attackable;

public abstract class Person implements Attackable {

    private String name;


    protected Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
