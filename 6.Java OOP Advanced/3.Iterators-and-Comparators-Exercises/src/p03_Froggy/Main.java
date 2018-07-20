package p03_Froggy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] array = Arrays.stream(reader.readLine().split("[ ||,]+")).mapToInt(Integer::parseInt).toArray();

        Lake<Integer> lake = new Lake<>(Arrays.stream(array).boxed().toArray( Integer[]::new ));

        String result = "";

        for (Integer integer : lake) {
            result += integer + ", ";
        }

        System.out.println(result.substring(0,result.length() -2));



    }

}
