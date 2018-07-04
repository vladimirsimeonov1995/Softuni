package p06_BirthdayCelebration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Birthable> birthables = new ArrayList<>();

        getBirthables(reader, birthables);

        printSpecialYear(reader, birthables);


    }

    private static void printSpecialYear(BufferedReader reader, List<Birthable> birthables) throws IOException {
        String specialYear = reader.readLine();

        birthables.stream()
                .filter(x -> x.getBirthDate().endsWith(specialYear))
                .forEach(x -> System.out.println(x.getBirthDate()));
    }

    private static void getBirthables(BufferedReader reader, List<Birthable> birthables) throws IOException {
        while (true){
            String[] information = reader.readLine().split(" ");
            if("End".equals(information[0]))
                break;

            switchInformation(birthables, information);
        }
    }

    private static void switchInformation(List<Birthable> birthables, String[] information) {
        switch (information[0]){
            case "Citizen":
                getPerson(birthables, information);
                break;
            case "Pet":
                getPet(birthables, information);
                break;
            case "Robot":
                //ignore
                break;
        }
    }

    private static void getPet(List<Birthable> birthables, String[] information) {
        String petName = information[1];
        String petBirthDate = information[2];
        birthables.add(new Pet(petName,petBirthDate));
    }

    private static void getPerson(List<Birthable> birthables, String[] information) {
        String citizenName = information[1];
        int citizenAge = Integer.parseInt(information[2]);
        String citizenId = information[3];
        String citizenBirthDate = information[4];
        birthables.add(new Citizen(citizenName,citizenAge,citizenId,citizenBirthDate));
    }
}
