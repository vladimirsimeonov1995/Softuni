package p05_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Borned> bornables = new ArrayList<>();

        List<String> inputData =
                Arrays.stream(reader.readLine()
                        .split(" "))
                .collect(Collectors.toList());

        while (!inputData.get(0).equals("End")){

            if(inputData.get(0).equals("Citizen")){

                String name = inputData.get(1);
                Integer age = Integer.parseInt(inputData.get(2));
                String id = inputData.get(3);
                String birthdate = inputData.get(4);

                bornables.add(new Citizen(name,age,id, birthdate));
            }
            else if(inputData.get(0).equals("Pet")){
                String name = inputData.get(1);
                String birthdate = inputData.get(2);

                bornables.add(new Pet(name,birthdate));
            }



            inputData =
                    Arrays.stream(reader.readLine()
                            .split(" "))
                            .collect(Collectors.toList());
        }


        String specialYear = reader.readLine();

        bornables.forEach(borned -> {
            if(borned.getBirthdate().endsWith(specialYear))
                System.out.println(borned.getBirthdate());
        });

    }

}
