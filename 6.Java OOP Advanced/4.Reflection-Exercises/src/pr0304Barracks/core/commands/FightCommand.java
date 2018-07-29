package pr0304Barracks.core.commands;

import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.UnitFactory;

public class FightCommand implements Executable {

    @Override
    public String execute() {
        return "fight";
    }

}
