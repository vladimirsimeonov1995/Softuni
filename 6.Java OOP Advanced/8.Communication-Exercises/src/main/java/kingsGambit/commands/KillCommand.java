package kingsGambit.commands;

import kingsGambit.interfaces.Command;
import kingsGambit.interfaces.Deadable;

public class KillCommand implements Command {

    private Deadable person;

    public KillCommand(Deadable person) {
        this.person = person;
    }


    @Override
    public void execute() {
        this.person.kill();
    }
}
