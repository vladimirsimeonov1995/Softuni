package app.ccb.services;

import app.ccb.domain.dtos.ClientImportDto;
import app.ccb.domain.entities.Card;
import app.ccb.domain.entities.Client;
import app.ccb.domain.entities.Employee;
import app.ccb.repositories.ClientRepository;
import app.ccb.repositories.EmployeeRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ClientServiceImpl implements ClientService {

    private final String CLIENT_IMPORT_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\Workshop - MVC project - Spring MVC and Spring Data\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\clients.json";

    private final ClientRepository clientRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final ValidationUtil validationUtil;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, EmployeeRepository employeeRepository, ValidationUtil validationUtil) {
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean clientsAreImported() {
        return this.clientRepository.count() != 0;
    }

    @Override
    public String readClientsJsonFile() throws IOException {
        return this.fileUtil.readFile(CLIENT_IMPORT_FILE_PATH);
    }

    @Override
    public String importClients(String clients) {
        ClientImportDto[] clientImportDtos = this.gson.fromJson(clients, ClientImportDto[].class);

        StringBuilder importResult = new StringBuilder();

        for (ClientImportDto clientImportDto : clientImportDtos) {

            Employee employeeEntity = this.employeeRepository
                    .findByFirstNameAndLastName(
                            clientImportDto.getEmployeeName().split("\\s+")[0],
                            clientImportDto.getEmployeeName().split("\\s+")[1]
                    ).orElse(null);

            if(!(this.validationUtil.isValid(clientImportDto)) || employeeEntity == null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Client clientEntity = this.modelMapper.map(clientImportDto, Client.class);
            clientEntity.setFullName(String.format("%s %s", clientImportDto.getFirstName(), clientImportDto.getLastName()));
            clientEntity.getEmployees().add(employeeEntity);

            if(this.clientRepository.findByFullName(clientEntity.getFullName()).orElse(null) != null){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            this.clientRepository.saveAndFlush(clientEntity);

            importResult
                    .append(String.format("Successfully imported Client - %s.", clientEntity.getFullName()))
                    .append(System.lineSeparator());
        }

        return importResult.toString().trim();
    }

    @Override
    public String exportFamilyGuy() {

        StringBuilder result = new StringBuilder();

        Client familyGuy = this.clientRepository
                .findFamilyGuy()
                .stream()
                .findFirst()
                .orElse(null);

        if(familyGuy == null){
            result.append("No Family Guy found").append(System.lineSeparator());
        }else{
            result.append(String.format("Full Name: %s",familyGuy.getFullName()))
                    .append(System.lineSeparator());

            result.append(String.format("Age: %d", familyGuy.getAge()))
                    .append(System.lineSeparator());

            result.append(String.format("Bank Account: %s",
                    familyGuy.getBankAccount().getAccountNumber()))
                    .append(System.lineSeparator());

            for (Card card : familyGuy.getBankAccount().getCards()) {
                result.append(String.format("   Card Number %s", card.getCardNumber()))
                        .append(System.lineSeparator());

                result.append(String.format("   Card Status: %s", card.getCardStatus()))
                        .append(System.lineSeparator());
            }
        }

        return result.toString().trim();
    }
}
