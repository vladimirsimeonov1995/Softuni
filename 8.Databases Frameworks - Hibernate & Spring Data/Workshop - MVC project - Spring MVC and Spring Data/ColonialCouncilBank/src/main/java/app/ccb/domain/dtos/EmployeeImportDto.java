package app.ccb.domain.dtos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EmployeeImportDto {

    @Expose
    @SerializedName("full_name")
    private String fullName;

    @Expose
    private BigDecimal salary;

    @Expose
    @SerializedName("started_on")
    private String startedOn;

    @Expose
    @SerializedName("branch_name")
    private String branch;

    public EmployeeImportDto() {
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getStartedOn() {
        return startedOn;
    }

    public void setStartedOn(String startedOn) {
        this.startedOn = startedOn;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
