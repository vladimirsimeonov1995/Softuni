package p09_TraficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] signals = reader.readLine().split(" ");

        int cicles = Integer.parseInt(reader.readLine());

        String nextSignal = signals[signals.length -1];

        for (int i = 0; i < cicles; i++) {

            for (int j = 0; j < signals.length; j++) {

                TraficColors color = Enum.valueOf(TraficColors.class, signals[j]);

                signals[j] = color.next;

            }

            System.out.println(String.join(" ",signals));
        }

    }

}
