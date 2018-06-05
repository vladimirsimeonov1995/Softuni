import java.util.Arrays;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class ListOfPredicates {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        BiPredicate<Integer,Integer> checkDivisible = (a,b) -> a % b == 0;

        BiConsumer<int[],Integer> print = (a,b) -> {
            for (int i = 1; i <= b; i++) {
                boolean check = true;
                for (int anA : a) {
                    if (!checkDivisible.test(i, anA)) {
                        check = false;
                        break;
                    }
                }
                if(check)
                    System.out.printf("%d ",i);
            }
        };

        print.accept(numbers,n);


    }

}
