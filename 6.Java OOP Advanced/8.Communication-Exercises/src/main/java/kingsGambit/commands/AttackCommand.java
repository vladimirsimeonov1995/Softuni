package kingsGambit.commands;


import kingsGambit.persons.Person;
import kingsGambit.interfaces.Attackable;
import kingsGambit.interfaces.Command;

import java.util.Map;

public class AttackCommand implements Command {

    private Map<String , Person> persons;

    public AttackCommand(Map<String ,Person> persons) {
        this.persons = persons;
    }


    @Override
    public void execute() {
        persons.values().forEach(Attackable::attacked);
    }
}
