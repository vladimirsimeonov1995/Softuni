import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ExcellentStudents {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Student> students = new ArrayList<>();

        while (true){
            String[] line = reader.readLine().split(" ");
            if("END".equals(line[0]))
                break;

            String firstName = line[0];
            String lastName = line[1];
            List<Integer> marks = new ArrayList<>(){{
                for (int i = 2; i < 6; i++) {
                    add(Integer.parseInt(line[i]));
                }
            }};

            students.add(new Student(firstName,lastName,marks));
        }

        students.stream()
                .filter(student -> {
                    OptionalInt max = student.getMarks().stream().mapToInt(Integer::valueOf).max();
                    return max.isPresent() && max.getAsInt() == 6;
                })
                .forEach(student -> {
                    StringBuilder sb = new StringBuilder().append(student.getFirstName()).append(" ").append(student.getLastName());
                    System.out.println(sb);
                });



    }

    public static class Student{

        String firstName;
        String lastName;
        List<Integer> marks = new ArrayList<>();

        public Student() {
        }

        public Student(String firstName, String lastName, List<Integer> marks) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.marks = marks;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public List<Integer> getMarks() {
            return marks;
        }

        public void setMarks(List<Integer> marks) {
            this.marks = marks;
        }
    }

}
