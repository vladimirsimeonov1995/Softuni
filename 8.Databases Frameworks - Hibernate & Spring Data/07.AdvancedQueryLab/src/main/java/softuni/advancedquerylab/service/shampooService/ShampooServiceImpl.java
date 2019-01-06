package softuni.advancedquerylab.service.shampooService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.advancedquerylab.domain.entities.Ingredient;
import softuni.advancedquerylab.domain.entities.Label;
import softuni.advancedquerylab.domain.entities.Shampoo;
import softuni.advancedquerylab.domain.entities.enums.Size;
import softuni.advancedquerylab.repository.IngredientRepository;
import softuni.advancedquerylab.repository.LabelRepository;
import softuni.advancedquerylab.repository.ShampooRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;
    private final LabelRepository labelRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public ShampooServiceImpl(ShampooRepository shampooRepository, LabelRepository labelRepository, IngredientRepository ingredientRepository) {
        this.shampooRepository = shampooRepository;
        this.labelRepository = labelRepository;
        this.ingredientRepository = ingredientRepository;
    }


    @Override
    public List<String> selectShampoosBySize(String inputSize) {

        Size size = Size.valueOf(inputSize.toUpperCase());

        List<Shampoo> shampoos = shampooRepository.findAllBySizeOrderById(size);



        return shampoos.stream()
                .map(s -> {
                    return String.format("%s %s %.2flv.",
                            s.getBrand(),
                            s.getSize().name(),
                            s.getPrice());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectShampoosBySizeOrLabel(String size, String labelId) {

        Label label = labelRepository.findTopById(Long.parseLong(labelId));
        Size sizeObject = Size.valueOf(size.toUpperCase());

        List<Shampoo> shampoos = shampooRepository
                .findAllBySizeOrLabelOrderByPriceAsc(sizeObject, label);

        return shampoos.stream()
                .map(s -> {
                    return String.format("%s %s %.2flv.",
                            s.getBrand(),
                            s.getSize().name(),
                            s.getPrice());
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectShampoosByPriceAfter(String priceString) {

        BigDecimal price = BigDecimal.valueOf(Long.parseLong(priceString));

        return shampooRepository.findAllByPriceAfterOrderByPriceDesc(price)
                .stream()
                .map(s -> {
                    return String.format("%s %s %.2flv.",
                            s.getBrand(),
                            s.getSize().name(),
                            s.getPrice());
                }).collect(Collectors.toList());

    }

    @Override
    public List<String> selectIngredientsByNameStartsWith(String name) {

        return ingredientRepository.findAllByNameStartingWith(name.toUpperCase())
                .stream()
                .map(i -> String.format("%s", i.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> selectIngredientsByNames(List<String> names) {

        List<Ingredient> ingredients = ingredientRepository.findAllByName(names);

        return ingredients
                .stream()
                .map(Ingredient::getName)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getShampooCountWithPriceLowerThen(String price) {

        BigDecimal bigDecimalPrice = BigDecimal.valueOf(Double.parseDouble(price));

        return shampooRepository.countAllByPriceBefore(bigDecimalPrice);
    }

    @Override
    public List<String> selectShampoosWithIngredientIn(List<String> names) {

        List<Ingredient> ingredientsList = ingredientRepository.findAllByName(names);

        Set<Ingredient> ingredients = new HashSet<>(ingredientsList);

        return shampooRepository.findAllWithIngredientIn(ingredients)
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> selectShampoosWithIngredientByCountLessThen(String count) {
        return shampooRepository
                .findAllByIngredientsCountBefore(Long.parseLong(count))
                .stream()
                .map(Shampoo::getBrand)
                .collect(Collectors.toList());
    }

    @Override
    public Double selectIngredientsPriceByShampooName(String name) {

        Shampoo shampoo = shampooRepository.findAllByBrand(name);


        return shampooRepository.findIngredientsPriceByShampooName(shampoo);
    }


}
