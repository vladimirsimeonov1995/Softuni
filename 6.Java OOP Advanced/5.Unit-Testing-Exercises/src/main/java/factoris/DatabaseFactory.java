package factoris;

import models.Person;
import repository.Database;
import repository.PeopleDatabase;

import javax.naming.OperationNotSupportedException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DatabaseFactory {

    public static Database createDatabase(Integer[] array) throws OperationNotSupportedException {
        return new Database(array);
    }

    public static PeopleDatabase createPeopleDatabase(Map<Long, Person> map){

        return new PeopleDatabase(map);

    }

}
