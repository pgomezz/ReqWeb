package backingBeans;

import entities.Document;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.DocumentService;

@Named(value = "documentController")
@ViewScoped
public class DocumentController extends AbstractController<Document> implements Serializable {

    @Inject
    private DocumentService ejbService;

    public DocumentController() {
        super(Document.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
