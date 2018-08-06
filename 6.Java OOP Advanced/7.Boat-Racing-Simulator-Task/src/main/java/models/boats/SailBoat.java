package models.boats;

import Utility.Constants;
import contracts.IRace;

public class SailBoat extends BaseBoat {

    private int sailEfficiency;

    public SailBoat(String model, int weight, int sailEfficiency) {
        super(model, weight);
        this.setSailEfficiency(sailEfficiency);
    }

    public int getSailEfficiency() {
        return sailEfficiency;
    }

    private void setSailEfficiency(int sailEfficiency) {
        if (sailEfficiency < 1 || sailEfficiency > 100) {
            throw new IllegalArgumentException(Constants.IncorrectSailEfficiencyMessage);
        }
        this.sailEfficiency = sailEfficiency;
    }

    public double CalculateRaceSpeed(IRace race) {

        double result = (race.getWindSpeed() * ((double)this.getSailEfficiency() /100)) -
                super.getWeight() + ((double)race.getOceanCurrentSpeed() / 2 );

        return result;

    }

    @Override
    public boolean isItMotorBoat() {
        return false;
    }
}
