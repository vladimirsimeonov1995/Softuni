package workForce.employees;

import workForce.constants.Constants;
import workForce.employees.Employee;

public class PartTimeEmployee extends Employee {

    public PartTimeEmployee(String name) {
        super(name, Constants.PART_TIME_EMPLOYEE_WORKING_HOURS_PER_WEEK);
    }
}
