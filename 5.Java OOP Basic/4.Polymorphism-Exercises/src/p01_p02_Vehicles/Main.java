package p01_p02_Vehicles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String , Vehicle> vehicles  = new LinkedHashMap<>();

        getVehiclesInfo(reader, vehicles);

        int n = Integer.parseInt(reader.readLine());

        drive(reader, vehicles, n);

        for (Map.Entry<String, Vehicle> stringVehicleEntry : vehicles.entrySet()) {
            System.out.printf("%s: %.2f\n"
                    ,stringVehicleEntry.getKey()
                    ,stringVehicleEntry.getValue().getFuelQuantity());
        }


    }

    private static void drive(BufferedReader reader, Map<String, Vehicle> vehicles, int n) throws IOException {
        for (int i = 0; i < n; i++) {

            String[] commandInfo = reader.readLine().split(" ");

            switch (commandInfo[0]){
                case "Drive":
                    Double kilometres = Double.parseDouble(commandInfo[2]);
                    String result = vehicles.get(commandInfo[1]).driven(kilometres);
                    System.out.println(result);
                    break;
                case "Refuel":
                    Double liters = Double.parseDouble(commandInfo[2]);
                    vehicles.get(commandInfo[1]).refueled(liters);
                    break;
                case "DriveEmpty" :
                    Double litersForEmpty = Double.parseDouble(commandInfo[2]);
                    Bus currentBus = (Bus)vehicles.get("Bus");
                    System.out.println(currentBus.driveEmpty(litersForEmpty));
                    break;
            }



        }
    }

    private static void getVehiclesInfo(BufferedReader reader, Map<String, Vehicle> vehicles) throws IOException {
        String[] carInfo = reader.readLine().split(" ");

        vehicles.put("Car",new Car(Double.parseDouble(carInfo[1])
                ,Double.parseDouble(carInfo[2])
                ,Double.parseDouble(carInfo[3])));

        String[] truckInfo = reader.readLine().split(" ");

        vehicles.put("Truck",new Truck(Double.parseDouble(truckInfo[1])
                ,Double.parseDouble(truckInfo[2])
                ,Double.parseDouble(truckInfo[3])));

        String[] busInfo = reader.readLine().split(" ");

        vehicles.put("Bus",new Bus(Double.parseDouble(busInfo[1])
                ,Double.parseDouble(busInfo[2])
                ,Double.parseDouble(busInfo[3])));

    }

}
