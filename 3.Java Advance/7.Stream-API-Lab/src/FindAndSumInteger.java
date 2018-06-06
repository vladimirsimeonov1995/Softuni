import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

public class FindAndSumInteger {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        OptionalInt sum = Arrays.stream(reader.readLine().split(" "))
                .filter(x -> {
                    try {
                        Integer.parseInt(x);
                        return true;
                    }
                    catch (Exception e){
                        return false;
                    }
                })
                .mapToInt(Integer::parseInt)
                .reduce((x,y) -> x +y );

        System.out.println(sum.isPresent() ? sum.getAsInt() : "No match");



    }

}
