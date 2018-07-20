package p05_StrategyPattern;

import p05_StrategyPattern.comparators.CompareByAge;
import p05_StrategyPattern.comparators.ComparePersonByLengthOfName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeSet<Person> firstSet = new TreeSet<>(new ComparePersonByLengthOfName());
        TreeSet<Person> secondSet = new TreeSet<>(new CompareByAge());

        int n = Integer.parseInt(reader.readLine());

        for (int i = 0; i < n; i++) {
            String[] personParameters = reader.readLine().split(" ");

            String personName = personParameters[0];
            int personAge = Integer.parseInt(personParameters[1]);

            Person currentPerson = new Person(personName,personAge);

            firstSet.add(currentPerson);
            secondSet.add(currentPerson);
        }

        firstSet.forEach(System.out::println);
        secondSet.forEach(System.out::println);

    }

}
