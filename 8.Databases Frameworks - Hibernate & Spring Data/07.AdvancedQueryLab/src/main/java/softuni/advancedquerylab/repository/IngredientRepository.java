package softuni.advancedquerylab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import softuni.advancedquerylab.domain.entities.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    List<Ingredient> findAllByNameStartingWith(String name);

    @Query("SELECT i " +
            "FROM softuni.advancedquerylab.domain.entities.Ingredient AS i " +
            "WHERE i.name IN :names " +
            "ORDER BY i.price ASC")
    List<Ingredient> findAllByName(@Param(value = "names") List<String> names);

}
