package p07_FoodShortage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String,Buyer> buyers = new HashMap<>();

        getBuyers(reader, buyers);

        buyFood(reader, buyers);

        printSumOfFoods(buyers);


    }

    private static void printSumOfFoods(Map<String, Buyer> buyers) {
        int sum  = 0;

        for (Buyer buyer : buyers.values()) {
            sum += buyer.getFoodEaten();
        }

        System.out.println(sum);
    }

    private static void buyFood(BufferedReader reader, Map<String, Buyer> buyers) throws IOException {
        while (true){
            String name = reader.readLine();
            if("End".equals(name))
                break;

            if(buyers.containsKey(name))
                buyers.get(name).buyFood();
        }
    }

    private static void getBuyers(BufferedReader reader, Map<String, Buyer> buyers) throws IOException {
        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {

            String[] information = reader.readLine().split(" ");

            if(!buyers.containsKey(information[0])){

                if(information.length == 4)
                    addCitizen(buyers, information);
                else
                    addRebel(buyers, information);

            }
        }
    }

    private static void addRebel(Map<String, Buyer> buyers, String[] information) {
        String rebelName = information[0];
        int rebelAge = Integer.parseInt(information[1]);
        String rebelGroup = information[2];

        buyers.put(rebelName,new Rebel(rebelName,rebelAge,rebelGroup));
    }

    private static void addCitizen(Map<String, Buyer> buyers, String[] information) {
        String citizenName = information[0];
        int citizenAge = Integer.parseInt(information[1]);
        String citizenId = information[2];
        String citizenBirthDate = information[3];

        buyers.put(citizenName,new Citizen(citizenName,citizenAge,citizenId,citizenBirthDate));
    }

}
