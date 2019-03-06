package com.example.springbootsintro.domain.models.binding;

import org.springframework.lang.Nullable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class OfferFindBindingModel {

    private BigDecimal familyBudget;
    private String familyApartmentType;
    private String familyName;

    public OfferFindBindingModel() {
    }

    @DecimalMin("0.01")
    @Nullable
    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    @Nullable
    @NotBlank
    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    @Nullable
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
