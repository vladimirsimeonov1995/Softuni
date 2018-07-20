package p04_ComparingObjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Person> persons = new ArrayList<>();

        String singleLine = reader.readLine();

        addPersons(reader, persons, singleLine);

        int position = Integer.parseInt(reader.readLine()) -1;

        int equals = 0;

        for (Person person : persons) {
            if(persons.get(position).compareTo(person) == 0)
                equals++;
        }

        if(equals == 1)
            System.out.println("No matches");
        else {
            System.out.printf("%d %d %d\n",equals,persons.size()-equals,persons.size());
        }


    }

    private static void addPersons(BufferedReader reader, List<Person> persons, String singleLine) throws IOException {
        while (!"END".equals(singleLine)){

            String[] personAgrumens = singleLine.split(" ");

            String personName = personAgrumens[0];
            int personsAge = Integer.parseInt(personAgrumens[1]);
            String personTown = personAgrumens[2];

            persons.add(new Person(personName,personsAge,personTown));

            singleLine = reader.readLine();
        }
    }

}
