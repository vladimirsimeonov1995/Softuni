package models.boats;

import contracts.IEngine;
import contracts.IRace;

import java.util.Collections;
import java.util.List;

public class PowerBoat extends BaseBoat {

    private List<IEngine> engines;

    public PowerBoat(String model, int weight, List<IEngine> engines) {
        super(model, weight);
        this.setJetEngines(engines);
    }

    public List<IEngine> getEngines() {
        return Collections.unmodifiableList(this.engines);
    }

    private void setJetEngines(List<IEngine> engines) {
        this.engines = engines;
    }


    public double CalculateRaceSpeed(IRace race) {
        double result = (this.getEngines().get(0).getOutput() + this.getEngines().get(1).getOutput()) -
                super.getWeight() + ((double)race.getOceanCurrentSpeed() /5);

        return result;
    }

    @Override
    public boolean isItMotorBoat() {
        return true;
    }
}
