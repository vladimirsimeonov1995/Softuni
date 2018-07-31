package repository;

import models.Person;

import javax.naming.OperationNotSupportedException;
import java.util.Collections;
import java.util.Map;

public class PeopleDatabase {

    private Map<Long , Person> persons ;

    public PeopleDatabase(Map<Long, Person> persons) {
        this.setPersons(persons);
    }

    public Map<Long, Person> getPersons() {
        return Collections.unmodifiableMap(this.persons);
    }

    private void setPersons(Map<Long, Person> persons) {
        this.persons = persons;
    }

    public void add(Person person) throws OperationNotSupportedException {

        if(persons.containsKey(person.getId())){
            throw new OperationNotSupportedException();
        }

        for (Person person1 : persons.values()) {
            if(person1.getName().equals(person.getName()))
                throw new OperationNotSupportedException();
        }

        persons.put(person.getId(),person);

    }

    public void remove() throws OperationNotSupportedException {

        if(persons.isEmpty())
            throw new OperationNotSupportedException();

        Long idForRemove = 0L;

        for (Long aLong : persons.keySet()) {
            idForRemove = aLong;
        }

        persons.remove(idForRemove);

    }

    public Person findByUsername(String username) throws OperationNotSupportedException {

        if(username == null){
            throw new OperationNotSupportedException();
        }

        for (Person person : persons.values()) {
            if(username.equals(person.getName()))
                return person;
        }

        // No such person
        throw new OperationNotSupportedException();
    }

    public Person findById(Long id) throws OperationNotSupportedException {

        if(!persons.containsKey(id))
            throw new OperationNotSupportedException();

        return persons.get(id);
    }



}
