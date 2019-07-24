package jspexercises.web.beans;

import jspexercises.domain.models.service.DocumentServiceModel;
import jspexercises.domain.models.view.DocumentDetailViewModel;
import jspexercises.service.document.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class PrintDocumentBean {

    private DocumentDetailViewModel model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public PrintDocumentBean() {
    }

    @Inject
    public PrintDocumentBean(DocumentService documentService, ModelMapper modelMapper) {
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

    public void print() throws IOException {
        String id = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap().get("id");

        this.documentService.removeDocument(id);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/home.xhtml");
    }
}
