package workForce.employees;

import workForce.constants.Constants;
import workForce.employees.Employee;

public class StandartEmployee extends Employee {


    public StandartEmployee(String name) {
        super(name, Constants.STANDART_EMPLOYEE_WORKING_HOURS_PER_WEEK);
    }
}
