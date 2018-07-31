package factoris;

import models.Person;

public class PersonFactory {

    public static Person createPerson(Long id, String name){

        return new Person(id,name);

    }

}
