package p04_ShoppingSpree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Person> persons = new LinkedHashMap<>();

        Map<String, Product> products = new HashMap<>();

        if(getPersons(reader, persons)) {

            if(getProducts(reader, products)) {

                while (true) {
                    String[] buyProduct = reader.readLine().split(" ");

                    if ("END".equals(buyProduct[0]))
                        break;

                    System.out.println(persons.get(buyProduct[0]).addProduct(products.get(buyProduct[1])));
                }

                persons.entrySet().forEach(person -> {
                    StringBuilder sb = new StringBuilder();
                    sb.append(person.getKey()).append(" - ");
                    if (person.getValue().getBagOfProducts().isEmpty()) {
                        sb.append("Nothing bought");
                    }
                    person.getValue().getBagOfProducts().forEach(product -> {
                        sb.append(product.getName()).append(", ");
                    });
                    if (sb.charAt(sb.length() - 1) == ' ')
                        System.out.println(sb.substring(0, sb.length() - 2));
                    else
                        System.out.println(sb);
                });

            }
        }







    }

    private static boolean getProducts(BufferedReader reader, Map<String, Product> products) throws IOException {
        String[] productInfo = reader.readLine().split(";");
        for (String info : productInfo) {
            String[] infoForEveryProduct = info.split("=");
            Product product = null;

            try {
                product = new Product(infoForEveryProduct[0], Integer.parseInt(infoForEveryProduct[1]));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return false;
            }

            if (product != null)
                products.put(infoForEveryProduct[0], product);
        }

        return true;
    }


    private static boolean getPersons(BufferedReader reader, Map<String, Person> persons) throws IOException {
        String[] personsInfo = reader.readLine().split(";");
        for (String info : personsInfo) {
            String[] infoForEveryPerson = info.split("=");
            Person person = null;

            try {
                person = new Person(infoForEveryPerson[0], Integer.parseInt(infoForEveryPerson[1]));
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
                return false;
            }

            if (person != null)
                persons.put(infoForEveryPerson[0], person);
        }

        return true;
    }

}
