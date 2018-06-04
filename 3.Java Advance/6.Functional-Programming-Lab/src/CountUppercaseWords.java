import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CountUppercaseWords {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        List<String> words = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        Predicate<String> isUppercased = a -> {
            return Character.isLowerCase(a.charAt(0));
        };

        words.removeIf(isUppercased);

        System.out.println(words.size());
        words.forEach(a -> System.out.println(a));

    }
}
