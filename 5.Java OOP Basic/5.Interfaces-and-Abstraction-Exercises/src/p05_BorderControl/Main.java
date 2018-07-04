package p05_BorderControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Enterable> enterables = new ArrayList<>();

        getRobotsAndCitizens(reader, enterables);

        String fakeId = reader.readLine();

        enterables.stream()
                .filter(x -> x.getId().endsWith(fakeId))
                .forEach(x -> System.out.println(x.getId()));


    }

    private static void getRobotsAndCitizens(BufferedReader reader, List<Enterable> enterables) throws IOException {
        while (true){
            String[] information = reader.readLine().split(" ");
            if("End".equals(information[0]))
                break;

            if(information.length == 2){
                String robotModel = information[0];
                String robotId = information[1];

                enterables.add(new Robot(robotModel,robotId));
            }else {
                String citizenName = information[0];
                int citizenAge = Integer.parseInt(information[1]);
                String citizenId = information[2];

                enterables.add(new Citizen(citizenName,citizenAge,citizenId));
            }
        }
    }
}
