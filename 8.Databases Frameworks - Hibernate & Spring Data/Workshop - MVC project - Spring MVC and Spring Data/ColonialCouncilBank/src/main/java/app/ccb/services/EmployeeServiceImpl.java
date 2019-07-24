package app.ccb.services;

import app.ccb.domain.dtos.EmployeeImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.BranchRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final String EMPLOYEE_IMPORT_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\Workshop - MVC project - Spring MVC and Spring Data\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\employees.json";

    private final EmployeeRepository employeeRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final BranchRepository branchRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, BranchRepository branchRepository) {
        this.employeeRepository = employeeRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean employeesAreImported() {
        return this.employeeRepository.count() != 0;
    }

    @Override
    public String readEmployeesJsonFile() throws IOException {
        return this.fileUtil.readFile(EMPLOYEE_IMPORT_FILE_PATH);
    }

    @Override
    public String importEmployees(String employees) {
        EmployeeImportDto[] employeeImportDtos = this.gson.fromJson(employees, EmployeeImportDto[].class);
        StringBuilder importResult = new StringBuilder();

        for (EmployeeImportDto employeeImportDto : employeeImportDtos) {

            Branch branchEntity = this.branchRepository.findByName(employeeImportDto.getBranch()).orElse(null);

            if((!this.validationUtil.isValid(employeeImportDto)) || branchEntity == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Employee employeeEntity = this.modelMapper.map(employeeImportDto, Employee.class);
            employeeEntity.setFirstName(employeeImportDto.getFullName().split("\\s+")[0]);
            employeeEntity.setLastName(employeeImportDto.getFullName().split("\\s+")[1]);
            employeeEntity.setStartedOn(LocalDate.parse(employeeImportDto.getStartedOn()));
            employeeEntity.setBranch(branchEntity);

            employeeRepository.saveAndFlush(employeeEntity);

            importResult.append(String
                    .format("Successfully imported Employee - %s %s.",
                            employeeEntity.getFirstName(),
                            employeeEntity.getLastName())).append(System.lineSeparator());



        }

        return importResult.toString().trim();
    }

    @Override
    public String exportTopEmployees() {

        Employee topEmployee = this.employeeRepository.findTopEmployee().stream().findFirst().orElse(null);
        StringBuilder result = new StringBuilder();

        if(topEmployee == null){
            result.append("There is no top Employee!").append(System.lineSeparator());
        }else {
            result.append(String.format("Full Name:%s %s",
                    topEmployee.getFirstName(), topEmployee.getLastName()))
                    .append(System.lineSeparator());
            result.append(String.format("Salary:%s",
                    topEmployee.getSalary()))
                    .append(System.lineSeparator());
            result.append(String.format("Started On:%s",
                    topEmployee.getStartedOn()))
                    .append(System.lineSeparator());
            result.append("Clients:").append(System.lineSeparator());

            for (Client client : topEmployee.getClients()) {
                result.append(String.format("   %s", client.getFullName()))
                        .append(System.lineSeparator());
            }
        }


        return result.toString().trim();
    }
}
