package p03;

public class Ferrari implements Car {

    private static final String MODEL = "488-Spider";

    private String driverName;

    public Ferrari(String driverName) {
        this.setDriverName(driverName);
    }

    private void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String getDriverName() {
        return this.driverName;
    }

    @Override
    public String useBrakes() {
        return "Brakes!";
    }

    @Override
    public String useGasPedal() {
        return "Zadu6avam sA!";
    }

    @Override
    public String getModel() {
        return this.MODEL;
    }

    @Override
    public String toString() {
        return String.format("%s/%s/%s/%s",
                this.getModel(),this.useBrakes(),this.useGasPedal(),this.getDriverName());
    }
}
