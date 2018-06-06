import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FirstName {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> names = Arrays.stream(reader.readLine().split(" ")).collect(Collectors.toList());


        HashSet<Character> letters = new HashSet<>();

        Stream.of(reader.readLine().split("\\s+"))
                .map(s -> s.toLowerCase().charAt(0))
                .forEach(letters::add);


        Optional<String> name = names.stream()
                .sorted()
                .filter(x -> letters.contains(x.toLowerCase().charAt(0)))
                .findFirst();

        System.out.println(name.orElse("No match"));


    }

}
