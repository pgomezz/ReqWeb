package backingBeans;

import entities.UseCase;
import sessionBeans.UseCaseFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "useCaseController")
@ViewScoped
public class UseCaseController extends AbstractController<UseCase> implements Serializable {

    @Inject
    private UseCaseFacade ejbFacade;

    public UseCaseController() {
        super(UseCase.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
