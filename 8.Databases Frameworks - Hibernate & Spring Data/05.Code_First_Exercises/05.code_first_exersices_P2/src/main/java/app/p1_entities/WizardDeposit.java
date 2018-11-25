package app.p1_entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposit {

    private Integer id;
    private String firstName;
    private String lastName;
    private String notes;
    private Integer age;
    private String magicWandCreator;
    private Short magicWandSize;
    private String depositGroup;
    private LocalTime depositStartDate;
    private BigDecimal depositAmount;
    private BigDecimal depositInterest;
    private BigDecimal depositCharge;
    private LocalTime depositExpirationDate;
    private Boolean isDepositExpired;

    public WizardDeposit() {
    }

    public WizardDeposit(Integer id, String firstName,
                         String lastName, String notes,
                         Integer age, String magicWandCreator,
                         Short magicWandSize, String depositGroup,
                         LocalTime depositStartDate, BigDecimal depositAmount,
                         BigDecimal depositInterest, BigDecimal depositCharge,
                         LocalTime depositExpirationDate,
                         Boolean isDepositExpired) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setNotes(notes);
        setAge(age);
        setMagicWandCreator(magicWandCreator);
        setMagicWandSize(magicWandSize);
        setDepositGroup(depositGroup);
        setDepositStartDate(depositStartDate);
        setDepositAmount(depositAmount);
        setDepositInterest(depositInterest);
        setDepositCharge(depositCharge);
        setDepositExpirationDate(depositExpirationDate);
        setDepositExpired(isDepositExpired);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "first_name", length = 50, columnDefinition = "TEXT")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", length = 60,
            nullable = false, columnDefinition = "TEXT")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "notes", columnDefinition = "TEXT", length = 1000)
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name = "age", nullable = false)
    @Min(value = 0)
    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "magic_wand_creator", columnDefinition = "TEXT", length = 100)
    public String getMagicWandCreator() {
        return this.magicWandCreator;
    }

    public void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }

    @Column(name = "magic_wand_size")
    @Min(value = 1)
    public Short getMagicWandSize() {
        return this.magicWandSize;
    }

    public void setMagicWandSize(Short magicWandSize) {
        this.magicWandSize = magicWandSize;
    }

    @Column(name = "deposit_group", length = 20)
    public String getDepositGroup() {
        return this.depositGroup;
    }

    public void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }

    @Column(name = "deposit_start_date")
    public LocalTime getDepositStartDate() {
        return this.depositStartDate;
    }

    public void setDepositStartDate(LocalTime depositStartDate) {
        this.depositStartDate = depositStartDate;
    }

    @Column(name = "deposit_amount")
    public BigDecimal getDepositAmount() {
        return this.depositAmount;
    }

    public void setDepositAmount(BigDecimal depositAmount) {
        this.depositAmount = depositAmount;
    }

    @Column(name = "deposit_interest")
    public BigDecimal getDepositInterest() {
        return this.depositInterest;
    }

    public void setDepositInterest(BigDecimal depositInterest) {
        this.depositInterest = depositInterest;
    }

    @Column(name = "deposit_charge")
    public BigDecimal getDepositCharge() {
        return this.depositCharge;
    }

    public void setDepositCharge(BigDecimal depositCharge) {
        this.depositCharge = depositCharge;
    }

    @Column(name = "deposit_expiration_date")
    public LocalTime getDepositExpirationDate() {
        return this.depositExpirationDate;
    }

    public void setDepositExpirationDate(LocalTime depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }

    @Column(name = "is_deposit_expired")
    public Boolean getDepositExpired() {
        return this.isDepositExpired;
    }

    public void setDepositExpired(Boolean depositExpired) {
        isDepositExpired = depositExpired;
    }
}
