package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class App {

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence
                .createEntityManagerFactory("soft_uni");

        EntityManager entityManager = factory.createEntityManager();


    }

}
