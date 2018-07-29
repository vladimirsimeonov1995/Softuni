package pr0304Barracks.core.commands;

import pr0304Barracks.annotations.Inject;
import pr0304Barracks.contracts.Executable;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;

public class AddCommand implements Executable {


    @Inject
    private String[] data;

    @Inject
    private UnitFactory unitFactory;

    @Inject
    private Repository repository;

    @Override
    public String execute() {
        String unitType = this.data[1];
        Unit unitToAdd = null;

        unitToAdd = this.unitFactory.createUnit(unitType);

        this.repository.addUnit(unitToAdd);

        String output = unitType + " added!";

        return output;
    }
}
