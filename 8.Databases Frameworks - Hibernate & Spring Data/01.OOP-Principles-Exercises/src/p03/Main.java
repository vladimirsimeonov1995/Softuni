package p03;

import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)  {

        Scanner scanner = new Scanner(new InputStreamReader(System.in));

        Car car = new Ferrari(scanner.nextLine());

        System.out.println(car.toString());

    }

}
