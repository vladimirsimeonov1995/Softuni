import java.util.Scanner;
import java.util.function.Consumer;

public class ConsumerPrint {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);

        String[] names = input.nextLine().split("\\s+");

        Consumer<String[]> func = a -> {
            for (String name : a) {
                System.out.println(name);
            }
        };

        func.accept(names);

    }

}
