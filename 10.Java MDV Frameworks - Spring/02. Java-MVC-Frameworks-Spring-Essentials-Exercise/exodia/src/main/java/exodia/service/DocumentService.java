package exodia.service;

import exodia.domain.models.service.DocumentServiceModel;

import java.util.List;
import java.util.Optional;

public interface DocumentService {

    DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel);

    DocumentServiceModel findDocumentById(String id);

    List<DocumentServiceModel> findAllDocument();

    boolean printDocumentById(String id);

}
