package entities.ingredients.shampoos;

import entities.ingredients.labels.BasicLabel;
import enums.Size;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@DiscriminatorValue(value = "FS")
public class FiftyShades extends BasicShampoo{

    private static final String BRAND = "FiftyShades";

    private static final BigDecimal PRICE = new BigDecimal("6.69");

    private static final Size SIZE = Size.SMALL;

    public FiftyShades(){
    }

    public FiftyShades(BasicLabel classicLabel){
        super(BRAND, PRICE, SIZE, classicLabel);
    }

}
