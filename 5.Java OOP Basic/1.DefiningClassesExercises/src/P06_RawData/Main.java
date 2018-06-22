package P06_RawData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        List<Car> cars = new LinkedList<>();

        for (int i = 0; i < n; i++) {

            String[] currentCarParams = reader.readLine().split(" ");

            //“<Model>
            // <EngineSpeed> <EnginePower>
            // <CargoWeight> <CargoType>
            // <Tire1Pressure><Tire1Age>
            // <Tire2Pressure> <Tire2Age>
            // <Tire3Pressure><Tire3Age>
            // <Tire4Pressure> <Tire4Age>

            //ChevroletAstro 200 180 1000 fragile 1.3 1 1.5 2 1.4 2
            String model = currentCarParams[0];
            Engine currentEngine = new Engine(Integer.parseInt(currentCarParams[1])
                    ,Integer.parseInt(currentCarParams[2]));
            Cargo currentCargo = new Cargo(Integer.parseInt(currentCarParams[3])
                    ,currentCarParams[4]);
            Tire[] currentTires = new Tire[4];
            currentTires[0] = new Tire(Double.parseDouble(currentCarParams[5])
                    ,Integer.parseInt(currentCarParams[6]));
            currentTires[1] = new Tire(Double.parseDouble(currentCarParams[7])
                    ,Integer.parseInt(currentCarParams[8]));
            currentTires[2] = new Tire(Double.parseDouble(currentCarParams[9])
                    ,Integer.parseInt(currentCarParams[10]));
            currentTires[3] = new Tire(Double.parseDouble(currentCarParams[11])
                    ,Integer.parseInt(currentCarParams[12]));

            cars.add(new Car(model,currentCargo,currentEngine,currentTires));
        }

        if("flamable".equals(reader.readLine())){
            for (Car car : cars) {
                car.flamable();
            }
        }else{
            for (Car car : cars) {
                car.fragile();
            }
        }


    }

}
