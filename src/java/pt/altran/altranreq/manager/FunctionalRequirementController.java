package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.FunctionalRequirement;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.FunctionalRequirementFilter;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.TreeService;

@Named(value = "functionalRequirementController")
@ViewScoped
public class FunctionalRequirementController extends AbstractController<FunctionalRequirement> implements Serializable {

    @Inject
    private FunctionalRequirementService ejbFacade;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private FunctionalRequirementServiceBean frequirementBean;
    private FunctionalRequirement requirement;
    private FunctionalRequirement requirementedit;
    
    private List<FunctionalRequirement> items;
    
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
        super.setService(ejbFacade);
        requirement = super.prepareCreate(null);
        
    }  

    public boolean isFunctionalRequirementType()
    {
        return treeService.getSelected() instanceof FunctionalRequirement;
    }
    
    public FunctionalRequirement getFunctionalRequirement()
    {
        return (FunctionalRequirement)treeService.getSelected();
    }
    
    public List<FunctionalRequirement> getLista()
    {
        Project projectSelected = (Project)projectBean.getSelected();
        
        int idproj = Integer.parseInt(projectSelected.getIdProject().toString());
        FunctionalRequirementFilter reqFuncFilter = new FunctionalRequirementFilter();
        reqFuncFilter.setProjecto(idproj);
        return ejbFacade.findFunctionalRequirementByFilter(reqFuncFilter);
        //return items;
    }
    
    
    public String getState(int number) {
        return ejbFacade.getRequirementStateString(number);
    }
    
    
    
     @Override
    public void saveNew(ActionEvent event) {
        setSelected(requirement);
        ejbFacade.create(requirement);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void save(ActionEvent event) {
        
        FunctionalRequirement fr = (FunctionalRequirement)frequirementBean.getSelected();
        setSelected(fr);
        ejbFacade.edit(fr);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
          
    public boolean isFRequirementType()
    {
        return frequirementBean.getSelected() instanceof FunctionalRequirement;
    }
    
    public FunctionalRequirement getFRequirement()
    {
        return (FunctionalRequirement)frequirementBean.getSelected();
    }
    
    public void setFunctionalRequirement()
    {
        frequirementBean.setSelected(this.getSelected());
    }
    
    
    public void redirect(int option) throws IOException
    {
        frequirementBean.setSelected(this.getSelected());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        if (option == 1)
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/View_New.xhtml");
        if (option == 2)
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/Edit_New.xhtml");
    }
        
        
    }
    

