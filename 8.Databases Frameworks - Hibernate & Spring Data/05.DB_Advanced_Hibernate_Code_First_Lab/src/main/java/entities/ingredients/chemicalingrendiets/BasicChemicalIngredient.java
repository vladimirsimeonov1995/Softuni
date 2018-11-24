package entities.ingredients.chemicalingrendiets;

import contracts.ChemicalIngredient;
import entities.ingredients.basicingradients.BasicIngredient;

import javax.persistence.Column;
import java.math.BigDecimal;

public abstract class BasicChemicalIngredient extends BasicIngredient implements ChemicalIngredient {

    private String chemicalFormula;

    protected BasicChemicalIngredient(){

    }

    protected BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula){
        super(name,price);
        setChemicalFormula(chemicalFormula);
    }

    @Column(name = "chemical_formula")
    @Override
    public String getChemicalFormula() {
        return this.chemicalFormula;
    }

    @Override
    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }
}
