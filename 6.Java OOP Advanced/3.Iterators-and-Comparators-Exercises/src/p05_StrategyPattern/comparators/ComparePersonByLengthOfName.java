package p05_StrategyPattern.comparators;

import p05_StrategyPattern.Person;

import java.util.Comparator;

public class ComparePersonByLengthOfName implements Comparator<Person> {
    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        int compareByName = Integer.compare(firstPerson.getName().length(),secondPerson.getName().length());

        if(compareByName == 0){
            return String.valueOf(firstPerson.getName().charAt(0)).compareToIgnoreCase(String.valueOf(secondPerson.getName().charAt(0)));
        }

        return compareByName;
    }
}
