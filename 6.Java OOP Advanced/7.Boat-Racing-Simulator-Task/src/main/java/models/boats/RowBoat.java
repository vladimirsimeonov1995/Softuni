package models.boats;

import Utility.Validator;
import contracts.IRace;

public class RowBoat extends BaseBoat {

    private int oars;

    public RowBoat(String model, int weight, int oars) {
        super(model, weight);
        this.setOars(oars);
    }

    public int getOars() {
        return oars;
    }

    private void setOars(int oars) {
        Validator.ValidatePropertyValue(oars, "Oars");
        this.oars = oars;
    }


    public double CalculateRaceSpeed(IRace race) {
        double result = (this.getOars() * 100 ) - super.getWeight() + race.getOceanCurrentSpeed();

        return result;
    }

    @Override
    public boolean isItMotorBoat() {
        return false;
    }
}
