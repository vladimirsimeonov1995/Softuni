package p02_p03_CarShop;

public abstract class BaseCar implements Car {

    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduces;

    protected BaseCar(String model, String color, Integer horsePower, String countryProduces) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduces = countryProduces;
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
    public String getCountyProduced() {
        return this.countryProduces;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires"
                ,this.getModel()
                ,this.countryProduces
                ,this.TIRES);
    }


}
