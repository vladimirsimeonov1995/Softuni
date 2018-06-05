import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class FindEvensOrOdds {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] range = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        String command = scanner.nextLine();

        Predicate<Integer> predicate = a -> a % 2 == 0;

        if("odd".equals(command)){
            for (int i = range[0]; i <= range[1]; i++) {
                if(!predicate.test(i))
                    System.out.printf("%d ",i);
            }
        }
        else {
            for (int i = range[0]; i <= range[1]; i++) {
                if(predicate.test(i))
                    System.out.printf("%d ",i);
            }
        }

    }

}
