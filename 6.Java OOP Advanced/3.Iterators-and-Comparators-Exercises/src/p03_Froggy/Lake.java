package p03_Froggy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Lake<T> implements Iterable<T> {

    private List<T> list;

    public Lake(T... list){
        this.list = new ArrayList<>();
        Collections.addAll(this.list,list);
    }

    @Override
    public Iterator iterator() {
        return new Frog();
    }

    public final class Frog implements Iterator<T> {

        private int count = 0;

        @Override
        public boolean hasNext() {
            if(count %2 ==0){
                boolean check = count <= list.size() -1;
                if(check)
                    return check;
                else
                    count = 1;
            }

            return count <= list.size() -1;
        }

        @Override
        public T next() {
            int currentCount = count;
            count += 2;
            return list.get((currentCount));
        }
    }
}
