package p04_MordorsCrueltyPlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Gandalf gandalf = new Gandalf();

        String[] foods = reader.readLine().split(" ");

        gandalf.eat(foods);

        System.out.println(gandalf);

    }

}
