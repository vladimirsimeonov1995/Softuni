import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class FilterByAge {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        Map<String,Integer> people = new LinkedHashMap<>();

        for (int i = 0; i < n; i++) {
            String[] current = scanner.nextLine().split(", ");
            people.putIfAbsent(current[0],Integer.parseInt(current[1]));
        }

        BiConsumer<String,Integer> func = (x,y) -> {

            String[] print = scanner.nextLine().split(" ");

            if("older".equals(x)){
                for (String s : people.keySet()) {
                    if(people.get(s) >= y)
                        printMetod(print,s,people.get(s));
                }
            }
            else{
                for (String s : people.keySet()) {
                    if(people.get(s) < y)
                        printMetod(print,s,people.get(s));
                }
            }
        };

        func.accept(scanner.nextLine(),Integer.parseInt(scanner.nextLine()));



    }

    public static void printMetod(String[] print,String name,int age){

        if(print.length == 2){
            System.out.println(name + " - " + age);
        }
        else if("name".equals(print[0]))
            System.out.println(name);
        else
            System.out.println(age);
    }

}
