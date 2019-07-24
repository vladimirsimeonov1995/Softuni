package jspexercises.service.document;

import jspexercises.domain.models.service.DocumentServiceModel;

public interface DocumentService {

    String  schedule(DocumentServiceModel model);

    DocumentServiceModel getDocumentById(String id);

    boolean removeDocument(String id);

}
