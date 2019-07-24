package jspexercises.repository.documentrepo;

import jspexercises.domain.entities.Document;
import jspexercises.repository.GenericRepository;

public interface DocumentRepository extends GenericRepository<Document, String> {

    Document delete(String id);
}
