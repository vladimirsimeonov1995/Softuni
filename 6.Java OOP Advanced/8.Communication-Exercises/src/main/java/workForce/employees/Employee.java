package workForce.employees;

import workForce.interfaces.IEmployee;

public abstract class Employee implements IEmployee {

    private String name;
    private int workHoursPerWeek;

    protected Employee(String name, int workHoursPerWeek) {
        this.name = name;
        this.workHoursPerWeek = workHoursPerWeek;
    }

    public String getName() {
        return this.name;
    }

    public int getWorkHoursPerWeek() {
        return this.workHoursPerWeek;
    }
}
