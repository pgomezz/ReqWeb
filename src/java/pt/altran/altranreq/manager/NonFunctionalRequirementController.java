package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.NonFunctionalRequirement;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFunctionalFilter;
import pt.altran.altranreq.services.TreeService;

@Named(value = "nonFunctionalRequirementController")
@ViewScoped
public class NonFunctionalRequirementController extends AbstractController<NonFunctionalRequirement> implements Serializable {

    @Inject
    private RNFService ejbService;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private ProjectServiceBean projectBean;

    public NonFunctionalRequirementController() {
        super(NonFunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }
    
    public boolean isNFRType()
    {
        return treeService.getSelected() instanceof NonFunctionalRequirement;
    }
    
    public NonFunctionalRequirement getNFR()
    {
        return (NonFunctionalRequirement)treeService.getSelected();
    }
    
    public List<NonFunctionalRequirement> getLista()
    {
        Project projectSelected = (Project)projectBean.getSelected();
        
        int idproj = Integer.parseInt(projectSelected.getIdProject().toString());
        RNFunctionalFilter nReqFuncFilter = new RNFunctionalFilter();
        nReqFuncFilter.setProject(idproj);
        
        if (projectBean.isCateg())
        {
            nReqFuncFilter.setType(projectBean.getIdCategNRF());
            projectBean.setCateg(false);
            return ejbService.findRNFByFilter(nReqFuncFilter);
        }
        else
        {
            return ejbService.findRNFByFilter(nReqFuncFilter);
        }
        
        
        //return items;
    }

}
