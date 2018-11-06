package app.entities;

import app.db.annotations.Column;
import app.db.annotations.Entity;
import app.db.annotations.PrimaryKey;

@Entity(name = "exams")
public class Exam {

    @PrimaryKey(name = "id")
    private Long id;

    @Column(name = "failed_students_number")
    private int failedStudents;

    @Column(name = "passed_students_number")
    private int passedStudents;

    @Column(name = "name")
    private String name;

    @Column(name = "time_to_take_it")
    private int timeForExam;

    public Exam() {
    }

    public Exam(int failedStudents, int passedStudents, String name, int timeForExam) {
        setFailedStudents(failedStudents);
        setPassedStudents(passedStudents);
        setName(name);
        setTimeForExam(timeForExam);
    }


    public Long getId() {
        return this.id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public int getFailedStudents() {
        return this.failedStudents;
    }

    private void setFailedStudents(int failedStudents) {
        this.failedStudents = failedStudents;
    }

    public int getPassedStudents() {
        return this.passedStudents;
    }

    private void setPassedStudents(int passedStudents) {
        this.passedStudents = passedStudents;
    }

    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getTimeForExam() {
        return this.timeForExam;
    }

    private void setTimeForExam(int timeForExam) {
        this.timeForExam = timeForExam;
    }


    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", failedStudents=" + failedStudents +
                ", passedStudents=" + passedStudents +
                ", name='" + name + '\'' +
                ", timeForExam=" + timeForExam +
                '}';
    }
}
