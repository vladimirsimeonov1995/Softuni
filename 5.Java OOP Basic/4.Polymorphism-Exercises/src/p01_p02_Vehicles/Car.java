package p01_p02_Vehicles;

public class Car extends Vehicle {

    private final double fuelAdded = 0.9;

    public Car(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super.fuelQuantity = fuelQuantity;
        super.littersPerKm = littersPerKm + this.fuelAdded;
        super.tankCapacity = tankCapacity;
    }

    @Override
    public String driven(double kilometers) {
        double fuelComsumed = kilometers * super.getLittersPerKm();
        String returnedInfo = null;

        if (fuelComsumed <= super.getFuelQuantity()) {
            returnedInfo = "Car travelled " + super.formatter.format(kilometers) + " km";
            super.fuelQuantity = super.fuelQuantity - fuelComsumed;
        } else
            returnedInfo = "Car needs refueling";

        return returnedInfo;
    }

    @Override
    public void refueled(double fuel) {
        if ((super.getFuelQuantity() + fuel) > super.tankCapacity)
            System.out.println("Cannot fit fuel in tank");
        else if(fuel <= 0)
            System.out.println("Fuel must be a positive number");
        else
            super.fuelQuantity = super.getFuelQuantity() + fuel;
    }
}
