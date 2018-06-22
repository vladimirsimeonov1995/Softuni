package P07_CarSalesman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Map<String,Engine> engines = new HashMap<>();

        getEngine(reader, n, engines);

        int m = Integer.parseInt(reader.readLine());

        List<Car> cars = new LinkedList<>();

        GetCars(reader, engines, m, cars);

        for (Car car : cars) {
            System.out.print(car.toSting());
        }


    }

    private static void GetCars(BufferedReader reader, Map<String, Engine> engines, int m, List<Car> cars) throws IOException {
        for (int i = 0; i < m; i++) {

            String[] currentCar = reader.readLine().split(" ");

            String carModel = currentCar[0];
            String carEngineModel = currentCar[1];

            switch (currentCar.length){
                case 2:
                    cars.add(new Car(carModel,engines.get(carEngineModel)));
                    break;
                case 3:
                    if(currentCar[2].matches("\\d+")){
                        int carWeight = Integer.parseInt(currentCar[2]);
                        cars.add(new Car(carModel,engines.get(carEngineModel),carWeight));
                    }else {
                        cars.add(new Car(carModel,engines.get(carEngineModel),currentCar[2]));
                    }
                    break;
                case 4:{
                    int carWeight = Integer.parseInt(currentCar[2]);
                    cars.add(new Car(carModel,engines.get(carEngineModel),carWeight,currentCar[3]));
                    break;
                }
            }
        }
    }

    private static void getEngine(BufferedReader reader, int n, Map<String, Engine> engines) throws IOException {
        for (int i = 0; i < n; i++) {
            String[] currentEngine = reader.readLine().split(" ");

            String engineModel = currentEngine[0];
            int enginePower = Integer.parseInt(currentEngine[1]);

            switch (currentEngine.length){
                case 2:
                    engines.put(engineModel,new Engine(engineModel,enginePower));
                    break;
                case 3:
                    if(currentEngine[2].matches("\\d+")){
                        int engineDisplacement = Integer.parseInt(currentEngine[2]);
                        engines.put(engineModel,new Engine(engineModel,enginePower,engineDisplacement));
                    }
                    else {
                        engines.put(engineModel,new Engine(engineModel,enginePower,currentEngine[2]));
                    }
                    break;
                case 4:
                    int engineDisplacement = Integer.parseInt(currentEngine[2]);
                    engines.put(engineModel,new Engine(engineModel,enginePower,
                            engineDisplacement,currentEngine[3]));
                    break;
            }
        }
    }
}
