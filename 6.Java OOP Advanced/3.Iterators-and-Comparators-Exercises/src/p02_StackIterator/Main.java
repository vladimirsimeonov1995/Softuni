package p02_StackIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Stack<Integer> stack = new Stack<>();

        List<String> command = Arrays.stream(reader.readLine().split("[ ||,]+")).collect(Collectors.toList());

        while (!"END".equals(command.get(0))){
            switch (command.get(0)){

                case "Push":
                    command.remove(0);
                    int[] array = command.stream().mapToInt(Integer::parseInt).toArray();
                    stack.push(Arrays.stream(array).boxed().toArray( Integer[]::new ));
                    break;

                case "Pop":
                    stack.pop();

            }
            command = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());
        }

        iterStack(stack);
        iterStack(stack);

    }

    private static void iterStack(Stack<Integer> stack) {
        for (Integer element : stack) {
            System.out.println(element);
        }
    }


}
