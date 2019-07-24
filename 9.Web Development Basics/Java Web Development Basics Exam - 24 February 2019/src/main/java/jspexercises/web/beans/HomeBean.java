package jspexercises.web.beans;

import jspexercises.domain.models.view.DocumentDetailViewModel;
import jspexercises.domain.models.view.DocumentHomeViewModel;
import jspexercises.repository.documentrepo.DocumentRepository;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean {

    private List<DocumentHomeViewModel> documents;

    private DocumentRepository documentRepository;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(DocumentRepository documentRepository, ModelMapper modelMapper) {
        this.documentRepository = documentRepository;
        this.modelMapper = modelMapper;
        this.initDocuments();
    }

    private void initDocuments() {

        this.documents = this.documentRepository.findAll()
                .stream()
                .map(d -> {
                    return this.modelMapper.map(d, DocumentHomeViewModel.class);
                })
                .collect(Collectors.toList());

        for (DocumentHomeViewModel d : documents) {
            if (d.getTitle().length() > 15){
                d.setTitle(d.getTitle().substring(0, 11) + "...");
            }
        }
    }

    public List<DocumentHomeViewModel> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentHomeViewModel> documents) {
        this.documents = documents;
    }

    public void detail(String id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/document-detail.xhtml?id=" + id);
    }

    public void print(String id) throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/view/print-document.xhtml?id=" + id);
    }

}
