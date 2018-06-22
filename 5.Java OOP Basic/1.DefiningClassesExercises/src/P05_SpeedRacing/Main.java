package P05_SpeedRacing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader((new InputStreamReader(System.in)));

        Map<String,Car> cars = new LinkedHashMap<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] currentCar = reader.readLine().split(" ");

            cars.put(currentCar[0],new Car(currentCar[0],Double.parseDouble(currentCar[1]),Double.parseDouble(currentCar[2])));
        }

        while (true){
            String[] currentRide = reader.readLine().split(" ");
            if("End".equals(currentRide[0]))
                break;

            cars.get(currentRide[1]).travel(Double.parseDouble(currentRide[2]));
        }

        for (String car : cars.keySet()) {
            cars.get(car).toSting();
        }

    }

}
