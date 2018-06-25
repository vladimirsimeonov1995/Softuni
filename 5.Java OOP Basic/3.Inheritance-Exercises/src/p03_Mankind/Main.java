package p03_Mankind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] studentInfo = reader.readLine().split(" ");
        String[] workerInfo = reader.readLine().split(" ");

        try {
            Student student = new Student(studentInfo[0],studentInfo[1]
                    ,studentInfo[2]);
            Worker worker = new Worker(workerInfo[0],workerInfo[1]
                    ,Double.parseDouble(workerInfo[2]),Double.parseDouble(workerInfo[3]));

            System.out.println(student);
            System.out.println(worker);
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
//Ivan Ivanov 08
//Pesho Kirov 1590 10

    }

}
