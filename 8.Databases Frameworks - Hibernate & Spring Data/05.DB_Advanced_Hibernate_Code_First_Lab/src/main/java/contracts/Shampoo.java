package contracts;

import entities.ingredients.basicingradients.BasicIngredient;
import entities.ingredients.labels.BasicLabel;
import enums.Size;

import java.math.BigDecimal;
import java.util.Set;

public interface Shampoo {

    long getInt();

    void setInt();

    String getBrand();

    void setBrand();

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Size getSize();

    void setSize(Size size);

    BasicLabel getlabel();

    Set<BasicIngredient> getIngredients();

    void setIngredients(Set<BasicIngredient> ingredients);

}
