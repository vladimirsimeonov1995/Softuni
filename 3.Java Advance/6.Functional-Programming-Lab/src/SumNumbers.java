import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SumNumbers {


    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());

        Function<List<Integer>,Integer> sum = a -> {
            int suma = 0;
            for (Integer integer : a) {
                suma+= integer;
            }
            return suma;
        };

        Function<List<Integer>,Integer> count = a -> {
            return a.size();
        };

        System.out.println("Count = " + count.apply(numbers));
        System.out.println("Sum = " + sum.apply(numbers));





    }
}
