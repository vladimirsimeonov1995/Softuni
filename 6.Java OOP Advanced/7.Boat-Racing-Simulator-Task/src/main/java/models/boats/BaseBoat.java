package models.boats;

import Utility.Constants;
import Utility.Validator;
import contracts.IBoat;
import contracts.IRace;

public abstract class BaseBoat implements IBoat {

    private String model;
    private int weight;

    protected BaseBoat(String model, int weight) {
        this.setModel(model);
        this.setWeight(weight);
    }

    public String getModel() {
        return model;
    }

    private void setModel(String model) {
        Validator.ValidateModelLength(model, Constants.MinBoatModelLength);
        this.model = model;
    }

    public int getWeight() {
        return weight;
    }

    private void setWeight(int weight) {
        Validator.ValidatePropertyValue(weight, "Weight");
        this.weight = weight;
    }



}
