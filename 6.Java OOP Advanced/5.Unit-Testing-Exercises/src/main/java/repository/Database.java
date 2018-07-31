package repository;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;
import java.util.Collections;

public class Database {

    private Integer[] array;

    public Database(Integer[] array) throws OperationNotSupportedException {
        this.setArray(array);
    }

    private void setArray(Integer[] array) throws OperationNotSupportedException {
        if (array.length != 16) {
            throw new OperationNotSupportedException("Not exected capacity");
        }
        this.array = array;
    }

    public void add(Integer element) throws OperationNotSupportedException {

        if (null == element) {
            throw new OperationNotSupportedException();
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = element;
                return;
            }
        }

        throw new OperationNotSupportedException();
    }

    public void remove() throws OperationNotSupportedException {

        for (int i = array.length - 1; i >= 0; i--) {

            if (array[i] != null) {
                array[i] = null;
                return;
            }

        }

        throw new OperationNotSupportedException();

    }

    public Integer[] fetch() {

        return array;

    }


}
