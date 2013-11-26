package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
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
    
    
    private NonFunctionalRequirement nfrequirement;

    public NonFunctionalRequirementController() {
        super(NonFunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
        nfrequirement = super.prepareCreate(null);
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
    
    public String getState(int number) {
        return ejbService.getRequirementStateString(number);
    }
    public String getType(int number) {
        return ejbService.getRequirementTypeString(number);
    }
    
    
    @Override
    public void saveNew(ActionEvent event) {
        setSelected(nfrequirement);
        ejbService.create(nfrequirement);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
