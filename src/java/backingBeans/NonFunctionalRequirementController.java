package backingBeans;

import entities.NonFunctionalRequirement;
import sessionBeans.NonFunctionalRequirementFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "nonFunctionalRequirementController")
@ViewScoped
public class NonFunctionalRequirementController extends AbstractController<NonFunctionalRequirement> implements Serializable {

    @Inject
    private NonFunctionalRequirementFacade ejbFacade;

    public NonFunctionalRequirementController() {
        super(NonFunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
