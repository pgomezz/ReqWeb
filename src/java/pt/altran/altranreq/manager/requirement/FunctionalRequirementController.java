package pt.altran.altranreq.manager.requirement;

import java.io.IOException;
import pt.altran.altranreq.entities.FunctionalRequirement;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.FunctionalRequirementFilter;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.TreeService;

@Named(value = "functionalRequirementController")
@ViewScoped
public class FunctionalRequirementController extends AbstractController<FunctionalRequirement> implements Serializable {

    @Inject
    private FunctionalRequirementService functionalRequirementService;

    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject 
    private TreeService treeService;

    @Inject
    private FunctionalRequirementServiceBean functionalRequirementBean;
    
    private FunctionalRequirement functionalRequirement;
  
    public String getNameProject (){
        return ((Project)projectBean.getSelected()).getName();
    }
    
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
        super.setService(functionalRequirementService);
    }
    
    @Override
    public void delete(ActionEvent event) {
        try {
            Project currentProject = (Project)projectBean.getSelected();
            getFRequirement().setIdProject(currentProject);
        
            setSelected(getFRequirement());
            functionalRequirementService.remove(getFRequirement());
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("MyBundle").getString("FunctionalRequirementDeleted"));
            
        } catch (Exception e) {
            JsfUtil.addErrorMessage(ResourceBundle.getBundle("/project").getString("ErrorMessage"));
       }
    }

    public boolean isFunctionalRequirementType() {
        return treeService.getSelected() instanceof FunctionalRequirement;
    }

    public FunctionalRequirement getFunctionalRequirement() {
        return (FunctionalRequirement) treeService.getSelected();
    }

    public List<FunctionalRequirement> getLista() {
        Project projectSelected = (Project) projectBean.getSelected();

        int idproj = Integer.parseInt(projectSelected.getIdProject().toString());
        FunctionalRequirementFilter reqFuncFilter = new FunctionalRequirementFilter();
        reqFuncFilter.setProjecto(idproj);
        return functionalRequirementService.findFunctionalRequirementByFilter(reqFuncFilter);
       
    }

    public String getState(int number) {
        return functionalRequirementService.getRequirementStateString(number);
    }


    public boolean isFRequirementType() {
        return functionalRequirementBean.getSelected() instanceof FunctionalRequirement;
    }

    public FunctionalRequirement getFRequirement() {
       return (FunctionalRequirement)functionalRequirementBean.getSelected();
    }

    public void setFunctionalRequirement() {
        functionalRequirementBean.setSelected(this.getSelected());
    }

    public void redirect(int option) throws IOException {
        if (option == 1) {
            functionalRequirement = super.prepareCreate(null);
        } else {
            functionalRequirementBean.setSelected(this.getSelected());
        }
    }
    
    
    
    
    
    
    
    
    
    
}
