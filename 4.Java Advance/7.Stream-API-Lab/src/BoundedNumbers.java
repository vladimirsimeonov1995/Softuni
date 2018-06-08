import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoundedNumbers {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> bouders = Arrays.stream(reader.readLine().split("\\s+"))
                                    .sorted()
                                    .map(Integer::parseInt)
                                    .collect(Collectors.toList());

        Arrays.stream(reader.readLine().split(" "))
                .map(Integer::parseInt)
                .filter(x -> x >= bouders.get(0) && x <= bouders.get(1))
                .forEach(x -> System.out.printf("%d ",x));


    }

}
