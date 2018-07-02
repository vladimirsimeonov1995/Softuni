package p01_p02_Vehicles;

public class Truck extends Vehicle {


    public Truck(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super.fuelQuantity = fuelQuantity;
        super.littersPerKm = littersPerKm + 1.6;
        super.tankCapacity = tankCapacity;
    }

    @Override
    protected String driven(double kilometers) {
        double fuelComsumed = kilometers * super.getLittersPerKm();
        String returnedInfo = null;

        if (fuelComsumed <= super.getFuelQuantity()) {
            returnedInfo = "Truck travelled " + super.formatter.format(kilometers) + " km";
            super.fuelQuantity = super.fuelQuantity - fuelComsumed;
        } else
            returnedInfo = "Truck needs refueling";

        return returnedInfo;
    }

    @Override
    protected void refueled(double fuel) {
        if ((super.getFuelQuantity() + fuel) > super.tankCapacity)
            System.out.println("Cannot fit fuel in tank");
        else if(fuel <= 0)
            System.out.println("Fuel must be a positive number");
        else
            super.fuelQuantity = super.getFuelQuantity() + (fuel * 0.95);
    }
}
