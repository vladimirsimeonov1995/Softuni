import entities.ingredients.basicingradients.BasicIngredient;
import entities.ingredients.basicingradients.Mint;
import entities.ingredients.basicingradients.Nettle;
import entities.ingredients.chemicalingrendiets.AmmoniumChloride;
import entities.ingredients.labels.BasicLabel;
import entities.ingredients.shampoos.BasicShampoo;
import entities.ingredients.shampoos.FreshNuke;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();

        entityManager.getTransaction().begin();

        BasicIngredient am= new AmmoniumChloride();
        BasicIngredient mint = new Mint();
        BasicIngredient nettle = new Nettle();

        BasicLabel label = new BasicLabel("Fresh Nuke Shampoo",
                "Contains mint and nettle");

        BasicShampoo shampoo = new FreshNuke(label);

        entityManager.persist(label);

        entityManager.getTransaction().commit();
        entityManager.clear();



    }

}
