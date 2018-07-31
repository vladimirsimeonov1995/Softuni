package tests;

import factoris.DatabaseFactory;
import factoris.PersonFactory;
import models.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.PeopleDatabase;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class PeopleDatabaseTest {

    private static final String PERSON_NAME = "Pesho";
    private static final Long PERSON_ID = 10L;
    private static final String SECOND_PERSON_NAME = "Gosho";
    private static final Long SECOND_PERSON_ID = 20L;
    private static final int EXPECTED_SIZE_AFTER_ADD = 2;
    private static final int ZERO = 0;

    private PeopleDatabase database;
    private Person person;

    @Before
    public void initializedObjects() {

        person = PersonFactory.createPerson(PERSON_ID, PERSON_NAME);

        Map<Long, Person> persons = new LinkedHashMap<Long, Person>();
        persons.put(PERSON_ID, this.person);

        this.database = DatabaseFactory.createPeopleDatabase(persons);


    }

    @Test
    public void constructorCorrectWork() throws NoSuchFieldException, IllegalAccessException {

        Field map = PeopleDatabase.class.getDeclaredField("persons");

        map.setAccessible(true);

        Assert.assertNotNull(map.get(this.database));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getterReturnUnmodifiableMap() {
        this.database.getPersons().put(PERSON_ID, this.person);

    }

    @Test(expected = IllegalStateException.class)
    public void addPersonWithNotUniqueID() {

        Person secondPerson = PersonFactory.createPerson(PERSON_ID, SECOND_PERSON_NAME);

        try {
            this.database.add(secondPerson);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }

    }

    @Test(expected = IllegalStateException.class)
    public void addPersonWithNotUniqueName() {

        Person secondPerson = PersonFactory.createPerson(SECOND_PERSON_ID, PERSON_NAME);

        try {
            this.database.add(secondPerson);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }

    }

    @Test
    public void addCorrectWork() throws OperationNotSupportedException {

        Person secondPerson = PersonFactory.createPerson(SECOND_PERSON_ID, SECOND_PERSON_NAME);

        this.database.add(secondPerson);

        Assert.assertEquals("Not expected length", EXPECTED_SIZE_AFTER_ADD, this.database.getPersons().size());
    }

    @Test
    public void removeCorrectWork() throws OperationNotSupportedException {

        this.database.remove();

        Assert.assertEquals("Not expected size of map after remove",
                ZERO, this.database.getPersons().size());
    }

    @Test(expected = IllegalStateException.class)
    public void removeFromEmptyDatabase() {

        try {
            this.database.remove();
            this.database.remove();
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void findByIdWithNotExistedId() {

        try {
            this.database.findById(SECOND_PERSON_ID);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    @Test
    public void findByIdReturningPerson() throws OperationNotSupportedException {

        Person testPerson ;

        testPerson = this.database.findById(PERSON_ID);

        Assert.assertNotNull(testPerson);

    }

    @Test(expected = IllegalStateException.class)
    public void findByUsernameWithNullUsername(){

        try {
            this.database.findByUsername(null);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void findByUsernameWithNotExistedUsername(){

        try {
            this.database.findByUsername(SECOND_PERSON_NAME);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    @Test
    public void findByUsernameReturnPerson() throws OperationNotSupportedException {

        Person testPerson;

        testPerson = this.database.findByUsername(PERSON_NAME);

        Assert.assertNotNull(testPerson);

    }


}
