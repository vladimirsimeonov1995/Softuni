import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AddVat {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String[] numbers = scanner.nextLine().split(", ");

        Function<String[],String[]> func = a -> {
            for (int i = 0; i < a.length; i++) {
                Double currentA = Double.parseDouble(a[i]);
                currentA *= 1.2;
                a[i] = currentA.toString();
            }
            return a;
        };

        numbers = func.apply(numbers);

        System.out.println("Prices with VAT:");
        for (String number : numbers) {
            System.out.printf("%.2f\n",Double.parseDouble(number));
        }





    }

}
