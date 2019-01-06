package softuni.advancedquerylab.service.shampooService;

import java.math.BigDecimal;
import java.util.List;

public interface ShampooService {

    List<String> selectShampoosBySize(String inputSize);

    List<String> selectShampoosBySizeOrLabel(String size, String labelId);

    List<String> selectShampoosByPriceAfter(String priceString);

    List<String> selectIngredientsByNameStartsWith(String name);

    List<String> selectIngredientsByNames(List<String> names);

    Integer getShampooCountWithPriceLowerThen(String price);

    List<String> selectShampoosWithIngredientIn(List<String> names);

    List<String> selectShampoosWithIngredientByCountLessThen(String count);

    Double selectIngredientsPriceByShampooName(String name);

}
