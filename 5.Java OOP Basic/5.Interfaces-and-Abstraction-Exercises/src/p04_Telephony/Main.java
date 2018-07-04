package p04_Telephony;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Smartphone smartphone = new Smartphone();

        String[] numbers = reader.readLine().split(" ");
        for (String number : numbers) {
            System.out.println(smartphone.calling(number));
        }

        String[] urls = reader.readLine().split(" ");
        for (String url : urls) {
            System.out.println(smartphone.browse(url));
        }

    }

}
