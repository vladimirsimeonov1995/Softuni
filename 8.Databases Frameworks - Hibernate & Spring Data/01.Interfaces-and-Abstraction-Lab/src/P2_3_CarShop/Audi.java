package P2_3_CarShop;

public class Audi implements Rentable {

    private String model;
    private String color;
    private Integer horsePower;
    private Integer minRentDays;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower
            , Integer minRentDays, Double pricePerDay) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.minRentDays = minRentDays;
        this.pricePerDay = pricePerDay;
    }

    private void setModel(String model) {
        this.model = model;
    }

    private void setColor(String color) {
        this.color = color;
    }

    private void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    private void setMinRentDays(Integer minRentDays) {
        this.minRentDays = minRentDays;
    }

    private void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public Integer getMinRentDay() {
        return this.minRentDays;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }



    @Override
    public String toString() {
        return String.format("This is %s  and he have %d tires",
                this.getModel(),TIRES);
    }
}
