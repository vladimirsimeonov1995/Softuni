package P06_RawData;

import java.util.Map;

public class Car {

    private String model;
    private Cargo cargo;
    private Engine engine;
    private Tire[] tires = new Tire[4];

    public Car(String model, Cargo cargo, Engine engine, Tire[] tires) {
        this.model = model;
        this.cargo = cargo;
        this.engine = engine;
        this.tires = tires;
    }

    public void fragile(){
        if("fragile".equals(this.cargo.getType())){
            double preasure =0;
            for (Tire tire : this.tires) {
                preasure += tire.getPressure();
            }
            preasure /= 4;
            if(preasure < 1){
                System.out.println(model);
            }
        }
    }

    public void flamable(){
        if("flamable".equals(this.cargo.getType()) && this.engine.getPower() > 250){
            System.out.println(this.model);
        }
    }


}
