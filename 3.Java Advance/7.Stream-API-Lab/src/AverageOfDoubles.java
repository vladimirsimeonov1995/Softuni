import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class AverageOfDoubles {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        OptionalDouble avarage = Arrays.stream(reader.readLine().split("\\s+"))
                .filter(x -> !x.equals(""))
                .mapToDouble(Double::parseDouble)
                .average();

        if(avarage.isPresent())
            System.out.printf("%.2f",avarage.getAsDouble());
        else
            System.out.println("No match");

    }

}
