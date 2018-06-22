package P05_SpeedRacing;

public class Car {

    private String model;
    private double fuel;
    private double fuelCoast;
    private int distance;

    public Car(String model, double fuel, double fuelCoast) {
        this.model = model;
        this.fuel = fuel;
        this.fuelCoast = fuelCoast;
        this.distance = 0;
    }

    public void travel(double distance){

        if(distance * this.fuelCoast <= this.fuel){
            this.fuel -= (distance*this.fuelCoast);
            this.distance += distance;
        }else {
            System.out.print("Insufficient fuel for the drive\n");
        }
    }

    public double getFuel() {
        return fuel;
    }

    public int getDistance() {
        return distance;
    }

    public void toSting(){
        System.out.printf("%s %.2f %d\n",this.model,this.getFuel(),this.getDistance());
    }
}
