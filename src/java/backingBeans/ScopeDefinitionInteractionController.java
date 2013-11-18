package backingBeans;

import entities.ScopeDefinitionInteraction;
import sessionBeans.ScopeDefinitionInteractionFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "scopeDefinitionInteractionController")
@ViewScoped
public class ScopeDefinitionInteractionController extends AbstractController<ScopeDefinitionInteraction> implements Serializable {

    @Inject
    private ScopeDefinitionInteractionFacade ejbFacade;

    public ScopeDefinitionInteractionController() {
        super(ScopeDefinitionInteraction.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
