import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;

public class AppliedArithmetic {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int[] numbers = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

        Function<int[],int[]> add = a -> {
            for (int i = 0; i < a.length; i++) {
                a[i] += 1;
            }
            return a;
        };

        Function<int[],int[]> multiply = a -> {
            for (int i = 0; i < a.length; i++) {
                a[i] *= 2;
            }
            return a;
        };

        Function<int[],int[]> substract = a -> {
            for (int i = 0; i < a.length; i++) {
                a[i] -= 1;
            }
            return a;
        };

        Consumer<int[]> print = a -> {
            for (int number : a) {
                System.out.printf("%d ",number);
            }
            System.out.println();
        };

        Consumer<String> switchCommand = s -> {
            switch (s){
                case "add":
                    add.apply(numbers);
                    break;
                case "multiply":
                    multiply.apply(numbers);
                    break;
                case "subtract":
                    substract.apply(numbers);
                    break;
                case "print":
                    print.accept(numbers);
                    break;
            }
        };

        while (true){
            String command = scanner.nextLine();
            if("end".equals(command))
                break;
            switchCommand.accept(command);

        }


    }

}
