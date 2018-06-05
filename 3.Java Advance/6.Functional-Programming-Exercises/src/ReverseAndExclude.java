import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReverseAndExclude {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());

        int n = scanner.nextInt();

        Predicate<Integer> divisibleByN = a -> a % n == 0;

        numbers.removeIf(divisibleByN);

        Collections.reverse(numbers);

        Consumer<List<Integer>> print = a -> {
            for (Integer number : a) {
                System.out.printf("%d ",number);
            }
        };

        print.accept(numbers);


    }

}
