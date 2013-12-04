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
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFtServiceBean;
import pt.altran.altranreq.services.RNFunctionalFilter;
import pt.altran.altranreq.services.TreeService;

@Named(value = "nonFunctionalRequirementController")
@ViewScoped
public class NonFunctionalRequirementController extends AbstractController<NonFunctionalRequirement> implements Serializable {

    @Inject
    private RNFService nonFunctionalRequirementService;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private RNFtServiceBean nonFunctionalRequirementBean;
    private NonFunctionalRequirement nonFunctionalRequirement;

    public NonFunctionalRequirementController() {
        super(NonFunctionalRequirement.class);
    }
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @PostConstruct
    public void init() {
        super.setService(nonFunctionalRequirementService);
        nonFunctionalRequirement = super.prepareCreate(null);
    }
    
    public String getNameProject (){
    
    
        return ((Project)projectBean.getSelected()).getName();
        
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
            return nonFunctionalRequirementService.findRNFByFilter(nReqFuncFilter);
        }
        else
        {
            return nonFunctionalRequirementService.findRNFByFilter(nReqFuncFilter);
        }
        
        
        //return items;
    }
    
    public String getState(int number) {
        return nonFunctionalRequirementService.getRequirementStateString(number);
    }
    public String getType(int number) {
        return nonFunctionalRequirementService.getRequirementTypeString(number);
    }
    
    
    @Override
    public void saveNew(ActionEvent event) {
        nonFunctionalRequirement.setIdProject((Project)projectBean.getSelected());
        setSelected(nonFunctionalRequirement);
        nonFunctionalRequirementService.create(nonFunctionalRequirement);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    @Override
    public void save(ActionEvent event) {
        
        NonFunctionalRequirement nfr = (NonFunctionalRequirement) nonFunctionalRequirementBean.getSelected();
        setSelected(nfr);
        nonFunctionalRequirementService.edit(nfr);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
          
    public boolean isNFRequirementType()
    {
        return nonFunctionalRequirementBean.getSelected() instanceof NonFunctionalRequirement;
    }
    
    public NonFunctionalRequirement getNFRequirement()
    {
        
        return (NonFunctionalRequirement)nonFunctionalRequirementBean.getSelected();
    }
    
    public void setFunctionalRequirement()
    {
        nonFunctionalRequirementBean.setSelected(this.getSelected());
    }
    
    
    public void redirect(int action) throws IOException
    {
        if (action == 1) {
            nonFunctionalRequirement = super.prepareCreate(null);
        } else {
            nonFunctionalRequirementBean.setSelected(this.getSelected());
        }}
    

}
