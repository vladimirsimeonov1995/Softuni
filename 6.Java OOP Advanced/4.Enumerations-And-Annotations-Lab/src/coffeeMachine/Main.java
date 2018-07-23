package coffeeMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        CoffeeMachine machine = new CoffeeMachine();

        while (true) {

            String[] command = reader.readLine().split(" ");
            if ("END".equals(command[0]))
                break;

            if (command.length == 1) {
                machine.insertCoin(command[0]);
            } else {
                machine.buyCoffee(command[0], command[1]);
            }
        }

        for (Coffee coffee : machine.coffeesSold()) {
            System.out.println(coffee);
        }


    }
}

