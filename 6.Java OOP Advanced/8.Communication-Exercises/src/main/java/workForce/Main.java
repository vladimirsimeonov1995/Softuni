package workForce;

import workForce.constants.Constants;
import workForce.employees.PartTimeEmployee;
import workForce.employees.StandartEmployee;
import workForce.repositories.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Repository repository = new Repository();

        while (true){

            String[] command = reader.readLine().split(" ");

            if(Constants.TERMINATE_COMMAND.equals(command[0]))
                break;

            switch (command[0]){

                case "PartTimeEmployee":
                    repository.addEmployee(new PartTimeEmployee(command[1]));
                    break;
                case "StandartEmployee":
                    repository.addEmployee(new StandartEmployee(command[1]));
                    break;
                case "Job":
                    repository.addJob(new Job(command[1],Integer.parseInt(command[2]),repository.getEmployees().get(command[3])));
                    break;
                case "Pass":
                    repository.passWeek();
                    break;
                case "Status":
                    repository.status();
                    break;
            }

        }

    }

}
