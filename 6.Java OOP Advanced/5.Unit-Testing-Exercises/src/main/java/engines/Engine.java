package engines;

import factoris.ListIteratorFantory;
import models.ListIterator;

import javax.naming.OperationNotSupportedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Engine {

    BufferedReader reader;

    public Engine(){

        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void start() throws IOException, NoSuchMethodException {

        String[] create = reader.readLine().split(" ");

        ListIterator listIterator;

        if(create.length > 1){
            String[] args = new String[create.length-1];

            for (int i = 1; i < create.length; i++) {
                args[i-1] = create[i];
            }

            listIterator = ListIteratorFantory.createListIterator(args);
        }else {
            listIterator = ListIteratorFantory.createListIterator();
        }



        while (true) {

            String command = reader.readLine();

            if("END".equals(command))
                break;

            Method method = ListIterator.class
                    .getDeclaredMethod(Character.toLowerCase(command.charAt(0)) + command.substring(1));

            method.setAccessible(true);

        try {
            System.out.println(method.invoke(listIterator));
        } catch (InvocationTargetException e) {
            System.out.println("Invalid Operation!");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


        }

    }

}
