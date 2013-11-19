package backingBeans;

import entities.FunctionalRequirement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.AbstractService;
import sessionBeans.FunctionalRequirementService;
import sessionBeans.FunctionalRequirementServiceImp;

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
