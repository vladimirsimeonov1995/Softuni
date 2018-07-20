package p05_StrategyPattern.comparators;

import p05_StrategyPattern.Person;

import java.util.Comparator;

public class CompareByAge implements Comparator<Person> {

    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        return Integer.compare(firstPerson.getAge(),secondPerson.getAge());
    }
}
