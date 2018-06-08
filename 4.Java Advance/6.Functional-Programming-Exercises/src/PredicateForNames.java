import java.util.Scanner;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

public class PredicateForNames {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());

        String[] names = scanner.nextLine().split("\\s+");

        BiPredicate<String,Integer> checkLength = (x,y) -> {
            return x.length() <= y;
        };

        Consumer<String[]> print = a -> {
            for (String name : a) {
                if(checkLength.test(name,number))
                    System.out.println(name);
            }
        };

        print.accept(names);

    }

}
