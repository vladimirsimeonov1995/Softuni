package jspexercises.web.beans;

import jspexercises.domain.models.binding.DocumentScheduleBindingModule;
import jspexercises.domain.models.service.DocumentServiceModel;
import jspexercises.service.document.DocumentService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class DocumentScheduleBean {

    private DocumentScheduleBindingModule model;

    private DocumentService documentService;
    private ModelMapper modelMapper;

    public DocumentScheduleBean() {
    }

    @Inject
    public DocumentScheduleBean( ModelMapper modelMapper, DocumentService documentService) {
        this.documentService = documentService;
        this.modelMapper = modelMapper;
        this.intiModel();
    }

    private void intiModel() {
        this.model = new DocumentScheduleBindingModule();
    }

    public DocumentScheduleBindingModule getModel() {
        return model;
    }

    public void setModel(DocumentScheduleBindingModule model) {
        this.model = model;
    }

    public void schedule() throws IOException {

        String id = this.documentService.schedule(this.modelMapper.map(model, DocumentServiceModel.class));

        if(id == null){
            throw new IllegalArgumentException("Something went wrong!");
        }

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();

        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/document-detail.xhtml?id=" + id);

    }
}
