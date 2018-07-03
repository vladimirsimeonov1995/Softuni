package p02_p03_CarShop;

public class Audi extends BaseCar implements Rentable{

    private Integer minRentDay;
    private Double pricePerDay;

    public Audi(String model, String color, Integer horsePower,
                String countryProduces, Integer minRentDay,
                Double pricePerDay) {

        super(model, color, horsePower, countryProduces);
        this.minRentDay = minRentDay;
        this.pricePerDay = pricePerDay;
    }




    @Override
    public Integer getMinRentDay() {
        return this.minRentDay;
    }

    @Override
    public Double getPricePerDay() {
        return this.pricePerDay;
    }




}
