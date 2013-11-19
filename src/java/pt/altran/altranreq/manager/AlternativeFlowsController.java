package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.AlternativeFlows;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.AlternativeFlowsService;

@Named(value = "alternativeFlowsController")
@ViewScoped
public class AlternativeFlowsController extends AbstractController<AlternativeFlows> implements Serializable {

    @Inject
    private AlternativeFlowsService ejbService;

    public AlternativeFlowsController() {
        super(AlternativeFlows.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
