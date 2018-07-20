package p02_StackIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Stack<T> implements Iterable<T> {

    private List<T> list;

    public Stack(){
        this.list = new ArrayList<>();
    }

    public void push(T... elements){
        for (T element : elements) {
            list.add(0,element);
        }
    }

    public void pop(){
        try {
            list.remove(0);
        }catch (IndexOutOfBoundsException ex){
            System.out.println("No elements");
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new StackIterator();
    }

    private final class StackIterator implements Iterator{

        private int count =0;

        @Override
        public boolean hasNext() {
            return count < list.size();
        }

        @Override
        public T next() {
            return list.get(count++);
        }
    }
}
