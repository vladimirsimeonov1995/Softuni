package models.boats;

import Utility.Validator;
import contracts.IEngine;
import contracts.IRace;

public class YachtBoat extends BaseBoat {

    private IEngine engine;
    private int cargoWeight;

    public YachtBoat(String model, int weight, IEngine engine, int cargoWeight) {
        super(model, weight);
        this.setEngine(engine);
        this.setCargoWeight(cargoWeight);
    }

    public IEngine getEngine() {
        return this.engine;
    }

    private void setEngine(IEngine engine) {
        this.engine = engine;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    private void setCargoWeight(int cargoWeight) {
        Validator.ValidatePropertyValue(cargoWeight, "Cargo Weight");
        this.cargoWeight = cargoWeight;
    }


    public double CalculateRaceSpeed(IRace race) {

        double result = this.getEngine().getOutput() - (super.getWeight() + this.getCargoWeight()) +
                ((double) race.getOceanCurrentSpeed() / 2);

        return result;
    }

    @Override
    public boolean isItMotorBoat() {
        return true;
    }
}
