package chushka.repository;

import chushka.domain.entities.Tube;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {

    private EntityManager entityManager;

    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("tube")
                .createEntityManager();
    }

    @Override
    public Tube save(Tube entity) {

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();

        return entity;
    }

    @Override
    public Tube findById(String id) {

        entityManager.getTransaction().begin();

        Tube product = entityManager
                .createQuery("SELECT t FROM tubes t " +
                        "WHERE t.id = :id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return product;
    }

    @Override
    public List<Tube> findAll() {
        entityManager.getTransaction().begin();

        List<Tube> products = entityManager
                .createQuery("SELECT t FROM tubes t", Tube.class)
                .getResultList();

        entityManager.getTransaction().commit();

        return products;
    }

    @Override
    public Tube findByName(String name) {
        entityManager.getTransaction().begin();

        Tube product = (Tube) entityManager
                .createQuery("SELECT t FROM tubes t WHERE t.name =:name")
                .setParameter("name", name)
                .getSingleResult();

        entityManager.getTransaction().commit();

        return product;
    }
}
