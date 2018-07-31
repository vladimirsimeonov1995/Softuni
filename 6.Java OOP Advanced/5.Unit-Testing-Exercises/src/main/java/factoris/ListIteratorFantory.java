package factoris;

import models.ListIterator;

public class ListIteratorFantory {

    public static final ListIterator createListIterator(String... strings){

        return new ListIterator(strings);

    }

}
