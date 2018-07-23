package codingTracker;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracker {

    static void printMethodsByAuthor(Class<?> cl){
        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Author annotation = method.getAnnotation(Author.class);

            System.out.println(annotation.name() + ": " + method.getName());
        }

    }
}

