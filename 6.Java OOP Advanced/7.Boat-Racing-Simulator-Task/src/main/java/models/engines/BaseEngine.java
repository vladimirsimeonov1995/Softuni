package models.engines;

import Utility.Constants;
import Utility.Validator;
import contracts.IEngine;

public abstract class BaseEngine implements IEngine {

    private String model;
    private int horsepower;
    private int displacement;

    protected BaseEngine(String model, int horsepower, int displacement) {
        this.setModel(model);
        this.setHorsepower(horsepower);
        this.setDisplacement(displacement);
    }

    public String getModel() {
        return this.model;
    }

    private void setModel(String model) {
        Validator.ValidateModelLength(model, Constants.MinBoatEngineModelLength);
        this.model = model;
    }

    public int getHorsepower() {
        return this.horsepower;
    }

    private void setHorsepower(int horsepower) {
        Validator.ValidatePropertyValue(horsepower, "Horsepower");
        this.horsepower = horsepower;
    }

    public int getDisplacement() {
        return displacement;
    }

    private void setDisplacement(int displacement) {
        Validator.ValidatePropertyValue(displacement, "Displacement");
        this.displacement = displacement;
    }

    protected int getOutput(int cachedOutput,int multiplier){

        if (cachedOutput != 0)
        {
            return cachedOutput;
        }

        cachedOutput = (this.getHorsepower() * multiplier) + this.getDisplacement();
        return cachedOutput;

    }

}
