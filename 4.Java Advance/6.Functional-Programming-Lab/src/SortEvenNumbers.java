import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SortEvenNumbers {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", ")).map(Integer::parseInt).collect(Collectors.toList());

        numbers.removeIf(n -> n % 2 != 0);

        System.out.println(numbers.toString().replace("]","").replace("[",""));

        numbers.sort(Integer::compareTo);

        System.out.println(numbers.toString().replace("]","").replace("[",""));

    }
}
