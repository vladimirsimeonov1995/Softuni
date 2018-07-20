package p01_ListyIterator;

import java.util.*;

public class ListyIterator<T> implements Iterable<T> {

    private List<T> list;
    private int count ;

    public ListyIterator(T... collection){
        this.list = new ArrayList<>();
        Collections.addAll(this.list,collection);
        this.count = 0;
    }

    public boolean Move(){
        if(this.HasNext()) {
            count++;
            return true;
        }

        return false;
    }

    public boolean HasNext(){
        return count < list.size()-1;
    }

    public void Print(){
        try {
            System.out.println(list.get(count));
        }catch (IndexOutOfBoundsException e){
            System.out.println("Invalid Operation!");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private final class ListIterator implements Iterator<T>{

        private int counter ;

        ListIterator(){
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return counter < list.size();
        }

        @Override
        public T next() {
            return list.get(counter++);
        }
    }
}
