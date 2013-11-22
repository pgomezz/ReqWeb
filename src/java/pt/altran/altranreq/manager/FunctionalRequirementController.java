package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.FunctionalRequirement;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.TreeService;

@Named(value = "functionalRequirementController")
@ViewScoped
public class FunctionalRequirementController extends AbstractController<FunctionalRequirement> implements Serializable {

    @Inject
    private FunctionalRequirementService ejbFacade;
    
    @Inject
    private TreeService treeService;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    public FunctionalRequirementController() {
        super(FunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }  

    public boolean isFunctionalRequirementType()
    {
        return treeService.getSelected() instanceof FunctionalRequirement;
    }
    
    public FunctionalRequirement getFunctionalRequirement()
    {
        return (FunctionalRequirement)treeService.getSelected();
    }
    
}
