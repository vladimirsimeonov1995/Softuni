import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PredicateParty {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        List<String> names = Arrays.stream(scanner.nextLine().split("\\s+")).collect(Collectors.toList());

        BiFunction<List<String>,String,List<String>> doubleStart = (list,text) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if(name.startsWith(text)) {
                    newList.add(name);
                    newList.add(name);
                }
                else {
                    newList.add(name);
                }
            }
            return newList;
        };

        BiFunction<List<String>,String,List<String>> endWith = (list,text) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if(name.endsWith(text)) {
                    newList.add(name);
                    newList.add(name);
                }
                else {
                    newList.add(name);
                }
            }
            return newList;
        };

        BiFunction<List<String>,Integer,List<String>> length = (list,number) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if(name.length() == number) {
                    newList.add(name);
                    newList.add(name);
                }
                else {
                    newList.add(name);
                }
            }
            return newList;
        };

        BiFunction<List<String>,String,List<String>> removeStart = (list,text) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if (!name.startsWith(text)) {
                    newList.add(name);
                }

            }
            return newList;
        };

        BiFunction<List<String>,String,List<String>> removeEnd = (list,text) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if(!name.endsWith(text)) {
                    newList.add(name);
                }
            }
            return newList;
        };

        BiFunction<List<String>,Integer,List<String>> removeLength = (list,number) -> {

            List<String> newList = new ArrayList<>();
            for (String name : list) {
                if(name.length() != number) {
                    newList.add(name);
                }
            }
            return newList;
        };


        while (true){

            String line = scanner.nextLine();
            if("Party!".equals(line)){
                break;
            }

            String[] command = line.split("\\s+");

            switch (command[1]){
                case "StartsWith":
                    if("Double".equals(command[0])) {
                        names = doubleStart.apply(names, command[2]);
                    }
                    else
                        names = removeStart.apply(names,command[2]);
                    break;
                case "EndsWith":
                    if("Double".equals(command[0]))
                        names =endWith.apply(names,command[2]);
                    else
                        names =removeEnd.apply(names,command[2]);
                    break;
                case "Length":
                    if("Double".equals(command[0]))
                        names = length.apply(names,Integer.parseInt(command[2]));
                    else
                        names = removeLength.apply(names,Integer.parseInt(command[2]));
                    break;
                }
        }

        if(names.size() == 0)
            System.out.println("Nobody is going to the party!");
        else
            System.out.println(String.join(", ",names) + " are going to the party!");



    }

}