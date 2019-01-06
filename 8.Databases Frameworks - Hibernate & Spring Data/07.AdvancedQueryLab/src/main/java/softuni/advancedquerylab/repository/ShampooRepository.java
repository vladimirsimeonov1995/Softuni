package softuni.advancedquerylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.advancedquerylab.domain.entities.Ingredient;
import softuni.advancedquerylab.domain.entities.Label;
import softuni.advancedquerylab.domain.entities.Shampoo;
import softuni.advancedquerylab.domain.entities.enums.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {

    List<Shampoo> findAllBySizeOrderById(Size size);

    Shampoo findAllByBrand(String brand);

    List<Shampoo> findAllBySizeOrLabelOrderByPriceAsc(Size size, Label label);

    List<Shampoo> findAllByPriceAfterOrderByPriceDesc(BigDecimal price);

    Integer countAllByPriceBefore(BigDecimal price);

    @Query("SELECT s " +
            "FROM softuni.advancedquerylab.domain.entities.Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "WHERE i IN :ingredients")
    List<Shampoo> findAllWithIngredientIn(
            @Param(value = "ingredients") Set<Ingredient> ingredients);

    @Query("SELECT s " +
            "FROM softuni.advancedquerylab.domain.entities.Shampoo AS s " +
            "JOIN s.ingredients AS i " +
            "GROUP BY s " +
            "HAVING COUNT(i) < :countIng")
    List<Shampoo> findAllByIngredientsCountBefore(
            @Param(value = "countIng") Long countIng);

    @Query("SELECT SUM(i.price) FROM softuni.advancedquerylab.domain.entities.Shampoo AS s JOIN s.ingredients AS i GROUP BY s HAVING s = :shampoo")
    Double findIngredientsPriceByShampooName(@Param(value = "shampoo") Shampoo shampoo);

}
