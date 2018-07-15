package p02_CustomList;

import java.util.Collections;
import java.util.List;

public class Sorter<T extends Comparable> {

    public static <T extends Comparable> void sort(List<T> list){

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).compareTo(list.get(j)) < 0)
                   swap(list,i,j);
            }
        }
    }

    private static <T extends Comparable> void swap(List<T> list , int firstIndex, int secondIndex){
        T temp = list.get(firstIndex);
        list.set(firstIndex,list.get(secondIndex));
        list.set(secondIndex,temp);
    }

}
