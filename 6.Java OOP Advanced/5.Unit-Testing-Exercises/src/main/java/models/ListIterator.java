package models;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListIterator {

    private final static int POINTER_STARTING_INDEX = 0;

    List<String> list ;

    private int pointer ;

    public ListIterator(String... strings){

        this.setList(strings);

        this.pointer = POINTER_STARTING_INDEX;

    }

    private void setList(String... strings) {
        this.list = new ArrayList<String>();

        Collections.addAll(list,strings);
    }

    public boolean hasNext() {

        return pointer != list.size() - 1;

    }

    public boolean move(){

        if(this.hasNext()){
            pointer ++;
            return true;
        }

        return false;

    }

    public String print() throws  IllegalAccessException {

        if(list.isEmpty()){
            throw new IllegalAccessException("Invalid Operation!");
        }

        return list.get(pointer);
    }

}
