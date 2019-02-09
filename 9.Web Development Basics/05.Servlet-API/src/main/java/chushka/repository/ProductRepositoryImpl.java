package chushka.repository;

import chushka.domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private EntityManager entityManager;

    public ProductRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("chushka")
                .createEntityManager();
    }

    @Override
    public Product save(Product entity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Product findById(String id) {

        entityManager.getTransaction().begin();

        Product product = entityManager
                .createQuery("SELECT p FROM products p " +
                        "WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return product;
    }

    @Override
    public List<Product> findAll() {
        entityManager.getTransaction().begin();

        List<Product> products = entityManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();

        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Product findByName(String name) {
        entityManager.getTransaction().begin();

        Product product = (Product) entityManager
                .createQuery("SELECT p FROM products p WHERE p.name = :name")
                .setParameter("name", name)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return product;
    }
}
