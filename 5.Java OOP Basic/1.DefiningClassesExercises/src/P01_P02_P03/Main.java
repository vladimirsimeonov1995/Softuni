package P01_P02_P03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(reader.readLine());

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] arguments = reader.readLine().split(" ");
            if(arguments.length > 1){
                persons.add(new Person(arguments[0],Integer.parseInt(arguments[1])));
            }else {
                persons.add(new Person(Integer.parseInt(arguments[0])));
            }
        }

        List<Person> sortedPersons = persons.stream()
                .filter(person -> person.getAge() > 30)
                .sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        for (Person sortedPerson : sortedPersons) {
            System.out.printf("%s - %d\n",sortedPerson.getName(),sortedPerson.getAge());
        }




    }


}
