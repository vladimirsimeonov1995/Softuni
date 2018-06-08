import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Consumer;

public class CustomComparator {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        String[] numbersString = scanner.nextLine().split("\\s+");

        Integer[] numbers = new Integer[numbersString.length];

        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        }

        Comparator<Integer> comparator = (a,b) -> {

            boolean first = a%2 ==0;
            boolean second = b%2 ==0;

            if(first && !second)
                return -1;
            else if(!first && second)
                return 1;
            else
                return a-b;

        };

        Arrays.sort(numbers,comparator);

        Consumer<Integer[]> print = a -> {
            for (Integer number : a) {
                System.out.printf("%d ",number);
            }
        };

        print.accept(numbers);

    }

}
