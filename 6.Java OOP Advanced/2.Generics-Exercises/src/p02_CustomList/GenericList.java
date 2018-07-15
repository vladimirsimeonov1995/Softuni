package p02_CustomList;

import java.util.*;
import java.util.function.Consumer;

public class GenericList<T extends Comparable> implements Iterable {

    private List<T> customList;

    public GenericList(){
        this.customList = new ArrayList<>();
    }

    public List<T> getCustomList() {
        return Collections.unmodifiableList(customList);
    }

    public void add(T item){
        this.customList.add(item);
    }

    public T remove(int index) {
        return this.customList.remove(index);
    }

    public boolean contains(T element){
        return this.customList.contains(element);
    }

    public void swap(int firstIndex, int secondIndex){
        T temp = this.customList.get(firstIndex);
        this.customList.set(firstIndex,this.customList.get(secondIndex));
        this.customList.set(secondIndex,temp);
    }

    public int countGreaterThan(T element){
        int count = 0;

        for (T currentElement : this.customList) {
            if(currentElement.compareTo(element) > 0)
                count++;
        }

        return count;
    }

    public T getMax(){
        T max = this.customList.get(0);

        for (T currentElement : this.customList) {
            if(currentElement.compareTo(max) > 0)
                max = currentElement;
        }

        return max;
    }

    public T getMin(){
        T min = this.customList.get(0);

        for (T currentElement : this.customList) {
            if(currentElement.compareTo(min) < 0)
                min = currentElement;
        }

        return min;
    }

    public void sort(){
        Sorter.sort(this.customList);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (T currentItem : this.customList) {
            sb.append(currentItem).append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {

    }

    @Override
    public Spliterator spliterator() {
        return null;
    }
}
