package p06_EqualityLogic;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<Person> personTreeSet = new TreeSet<>();
        HashSet<Person> personHashSet = new HashSet<>();

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            Person newPerson = createPerson(reader);

            personTreeSet.add(newPerson);
            personHashSet.add(newPerson);
        }

        System.out.println(personTreeSet.size());
        System.out.println(personHashSet.size());


    }

    private static Person createPerson(BufferedReader reader) throws IOException {
        String[] personArgs = reader.readLine().split(" ");

        String personName = personArgs[0];
        int personAge = Integer.parseInt(personArgs[1]);

        Person newPerson = new Person(personName,personAge);

        return newPerson;
    }

}
