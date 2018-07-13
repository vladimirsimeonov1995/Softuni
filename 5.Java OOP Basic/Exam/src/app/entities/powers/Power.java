package app.entities.powers;

import app.interfaces.comicWarInterfaces.SuperPower;

public class Power implements SuperPower {

    private String name;
    private double powerPoints;

    public Power(String name, double powerPoints) {
        this.setName(name);
        this.setPowerPoints(powerPoints);
    }

    private void setName(String name) {
        if (!name.matches("^@[\\w]+@"))
            throw new IllegalArgumentException("Super power name not in the correct format!");
        if (name.length() < 5)
            throw new IllegalArgumentException("Super power name not in the correct format!");

        this.name = name;
    }

    private void setPowerPoints(double powerPoints) {
        if(powerPoints < 0 )
            throw new IllegalArgumentException("Power points should be a possitive number");

        this.powerPoints = powerPoints;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getPowerPoints() {
        return this.powerPoints;
    }
}
