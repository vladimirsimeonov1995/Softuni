package p01_p02_Vehicles;

public class Bus extends Vehicle {


    public Bus(double fuelQuantity, double littersPerKm, double tankCapacity) {
        super.fuelQuantity = fuelQuantity;
        super.littersPerKm = littersPerKm;
        super.tankCapacity = tankCapacity;
    }

    public String driveEmpty(double kilometers){
        double fuelComsumed = kilometers * super.getLittersPerKm();
        String returnedInfo = null;

        if (fuelComsumed <= super.getFuelQuantity()) {
            returnedInfo = "Bus travelled " + super.formatter.format(kilometers) + " km";
            super.fuelQuantity = super.fuelQuantity - fuelComsumed;
        } else
            returnedInfo = "Bus needs refueling";

        return returnedInfo;
    }


    @Override
    protected String driven(double kilometers) {
        double fuelComsumed = kilometers * (super.getLittersPerKm() + 1.4);
        String returnedInfo = null;

        if (fuelComsumed <= super.getFuelQuantity()) {
            returnedInfo = "Bus travelled " + super.formatter.format(kilometers) + " km";
            super.fuelQuantity = super.fuelQuantity - fuelComsumed;
        } else
            returnedInfo = "Bus needs refueling";

        return returnedInfo;
    }

    @Override
    protected void refueled(double fuel) {
        if (fuel <= 0)
            System.out.println("Fuel must be a positive number");
        else if ((super.getFuelQuantity() + fuel) > super.tankCapacity)
            System.out.println("Cannot fit fuel in tank");
        else
            super.fuelQuantity = fuel + super.getFuelQuantity();
    }
}
