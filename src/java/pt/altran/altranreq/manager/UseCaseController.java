package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.UseCase;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.UseCaseService;

@Named(value = "useCaseController")
@ViewScoped
public class UseCaseController extends AbstractController<UseCase> implements Serializable {

    @Inject
    private UseCaseService ejbService;

    public UseCaseController() {
        super(UseCase.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
