import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Function<int[],Integer> func = a -> {
            int min = Integer.MAX_VALUE;
            for (Integer number : a) {
                if(number<min)
                    min = number;
            }
            return min;
        };

        System.out.println(func.apply(numbers));

    }

}
