package com.example.springbootsintro.domain.models.service;

import java.math.BigDecimal;

public class FamilyOfferServiceModel {

    private BigDecimal familyBudget;
    private String familyApartmentType;
    private String familyName;

    public FamilyOfferServiceModel() {
    }

    public BigDecimal getFamilyBudget() {
        return familyBudget;
    }

    public void setFamilyBudget(BigDecimal familyBudget) {
        this.familyBudget = familyBudget;
    }

    public String getFamilyApartmentType() {
        return familyApartmentType;
    }

    public void setFamilyApartmentType(String familyApartmentType) {
        this.familyApartmentType = familyApartmentType;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
