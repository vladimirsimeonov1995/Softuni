package jspexercises.repository.documentrepo;

import jspexercises.domain.entities.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryimpl implements DocumentRepository {

    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document entity) {
        entityManager.getTransaction().begin();
        try {
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            return entity;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Document findById(String id) {
        this.entityManager.getTransaction().begin();
        try {
            Document document = this.entityManager
                    .createQuery("SELECT d FROM Document d WHERE d.id = :id", Document.class)
                    .setParameter("id", id)
                    .getSingleResult();

            this.entityManager.getTransaction().commit();
            return document;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.getTransaction().begin();
        List<Document> documents = this.entityManager
                .createQuery("SELECT d FROM Document d ", Document.class)
                .getResultList();
        this.entityManager.getTransaction().commit();

        return documents;
    }

    @Override
    public Document update(Document entity) {
        this.entityManager.getTransaction().begin();
        try {
            Document updatedDocument = this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();

            return updatedDocument;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();

            return null;
        }
    }

    @Override
    public Document delete(String id) {

        try {
            Document document = this.findById(id);

            this.entityManager.getTransaction().begin();
            this.entityManager.remove(document);
            return document;

        } catch (Exception e) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }
}
