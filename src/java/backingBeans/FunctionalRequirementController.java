package backingBeans;

import entities.FunctionalRequirement;
import sessionBeans.FunctionalRequirementFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "functionalRequirementController")
@ViewScoped
public class FunctionalRequirementController extends AbstractController<FunctionalRequirement> implements Serializable {

    @Inject
    private FunctionalRequirementFacade ejbFacade;

    public FunctionalRequirementController() {
        super(FunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
