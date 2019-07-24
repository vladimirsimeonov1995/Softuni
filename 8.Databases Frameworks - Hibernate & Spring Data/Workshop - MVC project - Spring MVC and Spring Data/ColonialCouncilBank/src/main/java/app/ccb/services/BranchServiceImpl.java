package app.ccb.services;

import app.ccb.domain.dtos.BranchImportDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {

    private final String BRANCH_IMPORT_FILE_PATH = "C:\\Users\\Vlad\\Desktop\\Workshop - MVC project - Spring MVC and Spring Data\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\branches.json";

    private final BranchRepository branchRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, FileUtil fileUtil, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        return this.fileUtil.readFile(BRANCH_IMPORT_FILE_PATH);
    }

    @Override
    public String importBranches(String branchesJson) {
        StringBuilder importResult = new StringBuilder();
        BranchImportDto[] branchImportDtos = this.gson.fromJson(branchesJson, BranchImportDto[].class);

        for (BranchImportDto branchImportDto : branchImportDtos) {
            if(!this.validationUtil.isValid(branchesJson)){
                importResult.append("Error: Incorrect Data!").append(System.lineSeparator());
                continue;
            }

            Branch branchEntity = this.modelMapper.map(branchImportDto, Branch.class);
            this.branchRepository.saveAndFlush(branchEntity);

            importResult
                    .append(String.format("Successfully imported Branch - %s.", branchEntity.getName()))
                    .append(System.lineSeparator());
        }


        return importResult.toString().trim();
    }
}
