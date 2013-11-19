package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.NonFunctionalRequirement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.RNFService;

@Named(value = "nonFunctionalRequirementController")
@ViewScoped
public class NonFunctionalRequirementController extends AbstractController<NonFunctionalRequirement> implements Serializable {

    @Inject
    private RNFService ejbService;

    public NonFunctionalRequirementController() {
        super(NonFunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
