import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class StudentsEnrolledIn {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String facultyNumber = line[0];

            List<Integer> marks = new ArrayList<>(){{
                for (int i = 1; i < 5; i++) {
                    add(Integer.parseInt(line[i]));
                }
            }};

            students.add(new Student(facultyNumber,marks));
        }

        students.stream()
                .filter(student ->
                        student.getFacultyNumber().endsWith("14") || student.getFacultyNumber().endsWith("15"))
                .forEach(student -> {
                    student.getMarks().forEach(mark -> {
                        StringBuilder sb = new StringBuilder().append(mark).append(" ");
                        System.out.print(sb);
                    });
                    System.out.println();
                });







    }

    public static class Student{

        String facultyNumber;
        List<Integer> marks = new ArrayList<>();

        public Student() {
        }

        public Student(String facultyNumber, List<Integer> marks) {
            this.facultyNumber = facultyNumber;
            this.marks = marks;
        }

        public String getFacultyNumber() {
            return facultyNumber;
        }

        public void setFacultyNumber(String facultyNumber) {
            this.facultyNumber = facultyNumber;
        }

        public List<Integer> getMarks() {
            return marks;
        }

        public void setMarks(List<Integer> marks) {
            this.marks = marks;
        }
    }

}


