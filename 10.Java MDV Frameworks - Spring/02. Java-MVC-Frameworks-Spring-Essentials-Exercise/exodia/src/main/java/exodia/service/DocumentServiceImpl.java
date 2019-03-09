package exodia.service;

import exodia.domain.entities.Document;
import exodia.domain.models.service.DocumentServiceModel;
import exodia.repository.DocumentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DocumentServiceModel scheduleDocument(DocumentServiceModel documentServiceModel) {

        Document document = modelMapper.map(documentServiceModel, Document.class);

        try {
            documentRepository.saveAndFlush(document);
            return modelMapper.map(document, DocumentServiceModel.class);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public DocumentServiceModel findDocumentById(String id) {
        Document document = documentRepository.findById(id).orElse(null);

        if( document == null){
            return null;
        }

        return modelMapper.map(document, DocumentServiceModel.class);
    }

    @Override
    public List<DocumentServiceModel> findAllDocument() {
        return documentRepository.findAll()
                .stream()
                .map(d -> modelMapper.map(d, DocumentServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean printDocumentById(String id) {
        try {
            documentRepository.deleteById(id);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
