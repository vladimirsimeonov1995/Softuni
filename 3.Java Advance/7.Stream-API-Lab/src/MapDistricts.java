import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MapDistricts {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String ,List<Integer>> cities = getList(reader.readLine().split("\\s+"));

        int minPopulation = Integer.parseInt(reader.readLine());


        cities.entrySet().stream()
                .filter(x -> {
                   int sum = x.getValue().stream().mapToInt(Integer::byteValue).sum();
                   return sum > minPopulation;
                })
                .sorted((kv1,kv2) -> {
                    Optional<Integer> sum1 = kv1.getValue().stream().reduce((x, y) -> x + y);
                    Optional<Integer> sum2 = kv2.getValue().stream().reduce((x, y) -> x + y);

                    return Integer.compare(sum2.get(),sum1.get());
                })
                .forEach(x -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(x.getKey()).append(": ");
                    x.getValue().stream()
                            .sorted((a,b) -> Integer.compare(b,a))
                            .limit(5)
                            .forEach(a -> sb.append(a).append(" "));
                    System.out.println(sb);
                });



    }

    public static Map<String,List<Integer>> getList(String[] input){

        Map<String,List<Integer>> cities = new HashMap<>();

        for (String currentCity : input) {
            String[] city = currentCity.split(":");
            String cityName = city[0];
            Integer population = Integer.parseInt(city[1]);

            if(!cities.containsKey(cityName)){
                List<Integer> pop = new ArrayList<>(){{
                    add(population);
                }};
                cities.put(cityName,pop);
            }
            else {
                cities.get(cityName).add(population);
            }

        }

        return cities;

    }


}
