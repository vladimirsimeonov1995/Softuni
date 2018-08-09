package workForce;

import workForce.employees.Employee;

public class Job {

    private String name;
    private int hoursOfWorkNeeded ;
    private Employee employee;

    public Job(String name, int hoursOfWorkNeeded, Employee employee) {
        this.name = name;
        this.hoursOfWorkNeeded = hoursOfWorkNeeded;
        this.employee = employee;
    }

    public void update(){

        this.hoursOfWorkNeeded -= this.employee.getWorkHoursPerWeek();

        if(this.hoursOfWorkNeeded <= 0 ){
            System.out.printf("Job %s done!\n",this.name);
        }

    }

    public int getHoursOfWorkNeeded() {
        return this.hoursOfWorkNeeded;
    }

    @Override
    public String toString() {
        return String.format("Job: %s Hours Remaining: %d",this.name,this.hoursOfWorkNeeded);
    }
}
