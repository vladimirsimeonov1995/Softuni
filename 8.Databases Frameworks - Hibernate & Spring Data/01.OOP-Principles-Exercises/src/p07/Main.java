package p07;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Human> humans = new ArrayList<>();

        List<String> studentParams = getParameters(reader);

        List<String> workerParams = getParameters(reader);

        try {
            humans.add(getStudent(studentParams));
            humans.add(getWorker(workerParams));

            humans.forEach(System.out::println);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

    private static List<String> getParameters(BufferedReader reader) throws IOException {
        return   Arrays
                .stream(reader.readLine()
                        .split(" "))
                .collect(Collectors.toList());
    }

    private static Student getStudent(List<String> studentParams){

        String firstName = studentParams.get(0);
        String lastName = studentParams.get(1);
        String facNumber = studentParams.get(2);

        return new Student(firstName,lastName,facNumber);

    }

    private static Worker getWorker(List<String> workerParams){

        String firstName = workerParams.get(0);
        String lastName = workerParams.get(1);
        Double weekSalary = Double.parseDouble(workerParams.get(2));
        Double workHoursPerDay = Double.parseDouble(workerParams.get(3));

        return new Worker(firstName,lastName,weekSalary,workHoursPerDay);

    }
}
