package jspexercises.service.document;

import jspexercises.domain.entities.Document;
import jspexercises.domain.models.service.DocumentServiceModel;
import jspexercises.repository.documentrepo.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Inject
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String schedule(DocumentServiceModel model) {
        Document document = this.modelMapper.map(model, Document.class);

        if(this.documentRepository.save(document) != null){
            return document.getId();
        } else {
            return null;
        }
    }

    @Override
    public DocumentServiceModel getDocumentById(String id) {
        Document document = this.documentRepository.findById(id);

        if (document == null) {
            return null;
        }

        return this.modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public boolean removeDocument(String id) {

        if(this.documentRepository.delete(id) == null){
            return false;
        }

        return true;
    }
}
