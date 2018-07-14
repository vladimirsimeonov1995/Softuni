import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtils {

    public static <T extends Comparable> T getMin(List<T> list){

        if(list.isEmpty())
            throw new IllegalArgumentException();

        T min = list.get(0);

        for (T element : list) {
            if(element.compareTo(min) < 0)
                min = element;
        }

        return min;
    }

    public static <T extends Comparable> T getMax(List<T> list){

        if(list.isEmpty())
            throw new IllegalArgumentException();

        T max = list.get(0);

        for (T element : list) {
            if(element.compareTo(max) > 0)
                max = element;
        }

        return max;
    }

    public static  List<Integer> getNullIndices(List<?> list){

        List<Integer> nullList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if(list.get(i) == null)
                nullList.add(i);
        }

        return nullList;

    }

    public static <T> void flatten(List<T> destination,List<List<? extends T>> source){
        for (List<? extends T> ts : source) {
            destination.addAll(ts);
        }
    }

    public static <T> void addAll(List<T> destination, List<? extends T> source){
        destination.addAll(source);
    }
}
