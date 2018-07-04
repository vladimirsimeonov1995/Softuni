package p03_Ferrari;

public class Ferrari implements Car {

    private final String carName = "488-Spider";
    private String driverName;

    public Ferrari(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String getSimpleName() {
        return this.carName;
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String  pushTheGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s"
                ,this.getSimpleName(),this.useBrakes()
                ,this.pushTheGasPedal(),this.driverName);
    }
}
