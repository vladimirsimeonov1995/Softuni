package tests;

import factoris.ArrayFactory;
import factoris.DatabaseFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repository.Database;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;

public class DatabaseTest {

    private final static int CORRECT_CAPACITY = 16;
    private final static int UNCORRECT_CAPACITY = 15;
    private final static int TEN = 10;
    private final static int ARRAY_CAPACITY = 3;


    private Integer[] array;
    private Database database;

    @Before
    public void initializedObjects() {
        array = ArrayFactory.createIntegerArray(CORRECT_CAPACITY, 2, 3, 4); //ARRAY_CAPACITY
        try {
            database = DatabaseFactory.createDatabase(array);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testConstructorWithIntArray() {

        Database database = null;

        try {
            database = DatabaseFactory.createDatabase(array);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(database);
    }

    @Test(expected = IllegalStateException.class)
    public void testConstructorWithUncorrectCapacity() {

        array = ArrayFactory.createIntegerArray(UNCORRECT_CAPACITY, 2, 3, 4);

        try {
            Database database = DatabaseFactory.createDatabase(array);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }

    }

    @Test(expected = IllegalStateException.class)
    public void addNullElement() {

        try {
            this.database.add(null);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }

    }

    @Test
    public void addElement() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException {

        database.add(TEN);

        Field field = Database.class.getDeclaredField("array");
        field.setAccessible(true);

        Integer[] arrayInt = (Integer[]) field.get(database);

        int count = 0;

        for (Integer integer : arrayInt) {
            if(integer != null)
                count ++;
        }

        Assert.assertEquals("Didnt add element",ARRAY_CAPACITY +1, count);

    }

    @Test(expected = IllegalStateException.class)
    public void addElementInFullDatabase()  {

        this.array = ArrayFactory.createIntegerArray(16);

        for (int i = 0; i < 16; i++) {
            this.array[i] = TEN;
        }

        try {
            this.database = DatabaseFactory.createDatabase(array);
        } catch (OperationNotSupportedException e) {
            e.printStackTrace();
        }

        try {
            database.add(TEN);
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }

    }

    @Test
    public void removeElementCorrectWork() throws NoSuchFieldException
            , IllegalAccessException, OperationNotSupportedException {

        this.database.remove();

        Field field = Database.class.getDeclaredField("array");
        field.setAccessible(true);

        Integer[] arrayInt = (Integer[]) field.get(database);

        int count = 0;

        for (Integer integer : arrayInt) {
            if(integer != null)
                count ++;
        }

        Assert.assertEquals("Not corrent remove",ARRAY_CAPACITY -1, count);

    }

    @Test(expected = IllegalStateException.class)
    public void removeElementFromEmplyDatabase() throws OperationNotSupportedException {

        this.array = ArrayFactory.createIntegerArray(16);

        database = DatabaseFactory.createDatabase(array);

        try {
            database.remove();
        } catch (OperationNotSupportedException e) {
            throw new IllegalStateException();
        }
    }

    @Test
    public void fetchReturn() throws NoSuchFieldException, IllegalAccessException {

        Field field = Database.class.getDeclaredField("array");
        field.setAccessible(true);

        Assert.assertEquals((Integer[]) field.get(database),this.database.fetch());

    }

}
