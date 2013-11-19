package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.FunctionalRequirement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.AbstractService;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceImp;

@Named(value = "functionalRequirementController")
@ViewScoped
public class FunctionalRequirementController extends AbstractController<FunctionalRequirement> implements Serializable {

    @Inject
    private FunctionalRequirementService ejbFacade;

    public FunctionalRequirementController() {
        super(FunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
