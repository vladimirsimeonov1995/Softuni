package jspexercises.web.beans;

import jspexercises.domain.models.service.DocumentServiceModel;
import jspexercises.domain.models.view.DocumentDetailViewModel;
import jspexercises.service.document.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DocumentDetailViewBean {

    private DocumentDetailViewModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentDetailViewBean() {
    }

    @Inject
    public DocumentDetailViewBean(DocumentService documentService, ModelMapper modelMapper) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.initModel();
    }

    private void initModel() {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        DocumentServiceModel documentServiceModel = this.documentService.getDocumentById(id);

        if (documentServiceModel == null) {
            throw new IllegalArgumentException("Something went wrong!");
        }

        this.model = this.modelMapper.map(documentServiceModel, DocumentDetailViewModel.class);
    }

    public DocumentDetailViewModel getModel() {
        return model;
    }

    public void setModel(DocumentDetailViewModel model) {
        this.model = model;
    }
}
