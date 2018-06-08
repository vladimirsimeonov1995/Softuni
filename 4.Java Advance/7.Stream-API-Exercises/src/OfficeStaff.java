import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class OfficeStaff {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        Map<String,Map<String,Integer>> companies = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            List<String> line = Arrays.stream(reader.readLine().split("[ |-]+"))
                    .filter(x -> !x.equals(""))
                    .collect(Collectors.toList());

            String companyName = line.get(0);
            String product = line.get(2);
            int amount = Integer.parseInt(line.get(1));

            if(!companies.containsKey(companyName)){
                Map<String,Integer> products = new LinkedHashMap<>();
                products.put(product,amount);
                companies.put(companyName,products);
            }
            else {
                if(companies.get(companyName).containsKey(product)){
                    companies.get(companyName).put(product,companies.get(companyName).get(product) + amount);
                }
                else{
                    companies.get(companyName).put(product,amount);
                }
            }
        }

        companies.forEach((company,products) -> {
            StringBuilder sb = new StringBuilder()
                    .append(company).append(": ");
            products.forEach((product,amount) -> {
                sb.append(product).append("-").append(amount).append(", ");
            });
            System.out.println(sb.toString().substring(0,sb.length()-2));
        });


    }



}
