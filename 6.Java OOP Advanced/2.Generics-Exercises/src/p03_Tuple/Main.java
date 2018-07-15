package p03_Tuple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        String[] args1 = reader.readLine().split(" ");

        Tuple<Object,Object> tuple = new Tuple<>();

        tuple.setItem1(args1[0] + " " + args1[1]);
        tuple.setItem2(args1[2]);

        System.out.println(tuple.toString());

        args1 = reader.readLine().split(" ");

        tuple.setItem1(args1[0]);
        tuple.setItem2(args1[1]);

        System.out.println(tuple.toString());

        args1 = reader.readLine().split(" ");

        tuple.setItem1(args1[0]);
        tuple.setItem2(args1[1]);

        System.out.println(tuple.toString());

    }

}
