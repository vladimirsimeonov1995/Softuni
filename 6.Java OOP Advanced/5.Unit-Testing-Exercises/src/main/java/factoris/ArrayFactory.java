package factoris;

import java.util.Arrays;
import java.util.Collections;

public class ArrayFactory {

    public static Integer[] createIntegerArray(int capacity, int... integers){

        Integer[] array = new Integer[capacity];

        int count = 0;

        for (int integer : integers) {
            array[count] = integer;
            count++;

            if(count >= capacity)
                break;
        }

        return array;
    }

    public static String[] createStringArray(int capacity,String... words){
        String [] array = new String [capacity];

        int count = 0;

        for (String  word : words) {
            array[count] = word;
            count++;

            if(count >= capacity)
                break;
        }

        return array;
    }

}
