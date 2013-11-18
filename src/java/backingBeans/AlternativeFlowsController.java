package backingBeans;

import entities.AlternativeFlows;
import sessionBeans.AlternativeFlowsFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "alternativeFlowsController")
@ViewScoped
public class AlternativeFlowsController extends AbstractController<AlternativeFlows> implements Serializable {

    @Inject
    private AlternativeFlowsFacade ejbFacade;

    public AlternativeFlowsController() {
        super(AlternativeFlows.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
