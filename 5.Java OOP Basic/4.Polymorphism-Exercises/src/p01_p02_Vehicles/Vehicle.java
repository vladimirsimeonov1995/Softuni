package p01_p02_Vehicles;

import java.text.DecimalFormat;

public abstract class Vehicle {

    protected DecimalFormat formatter = new DecimalFormat("#.##");

    protected double fuelQuantity;
    protected double littersPerKm;
    protected double tankCapacity;

    public double getFuelQuantity() {
        return this.fuelQuantity;
    }

    public double getLittersPerKm() {
        return this.littersPerKm;
    }

    public double getTankCapacity() {
        return this.tankCapacity;
    }

    protected abstract String driven(double kilometers);

    protected abstract void refueled(double fuel);
}
