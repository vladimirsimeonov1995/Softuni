package factories;

import contracts.IBoat;
import contracts.IEngine;
import models.boats.PowerBoat;
import models.boats.RowBoat;
import models.boats.SailBoat;
import models.boats.YachtBoat;

import java.util.List;

public class BoatFactory {

    public static IBoat createRowBoat(String model, int weight, int oars){
        return new RowBoat(model,weight,oars);
    }

    public static IBoat createSailBoat(String model, int weight, int sailEfficiency){
        return new SailBoat(model,weight,sailEfficiency);
    }

    public static IBoat createPowerBoat(String model, int weight, List<IEngine > engines){
        return new PowerBoat(model, weight, engines);
    }

    public static IBoat createYachtBoat(String model, int weight, IEngine engine, int cargoWeight){
        return new YachtBoat(model, weight,  engine, cargoWeight);
    }



}
