import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class FindTheSmallestElement {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] number = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Function<int[],Integer> getSmaller = a -> {
            int smallestNumber = Integer.MAX_VALUE;
            int smallestIndex = 0;

            for (int i = 0; i < a.length; i++) {
                if(a[i] <= smallestNumber){
                    smallestNumber = a[i];
                    smallestIndex = i;
                }
            }
            return smallestIndex;
        };

        System.out.println(getSmaller.apply(number));
    }



}
