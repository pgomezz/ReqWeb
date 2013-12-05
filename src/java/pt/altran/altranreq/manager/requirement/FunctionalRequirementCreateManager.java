/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.requirement;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.FunctionalRequirementController;
import pt.altran.altranreq.services.FunctionalRequirementFilter;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.TreeService;

/**
 *
 * @author francisco
 */
@Named(value = "functionalRequirementCreateManager")
@ViewScoped
public class FunctionalRequirementCreateManager extends AbstractController<FunctionalRequirement> implements Serializable {


    @Inject
    private FunctionalRequirementService functionalRequirementService;

    @Inject
    private TreeService treeService;

    @Inject
    private ProjectServiceBean projectBean;

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

    public FunctionalRequirementCreateManager() {
        super(FunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setService(functionalRequirementService);
        
        super.setSelected(new FunctionalRequirement());
        

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

    @Override
    public void saveNew(ActionEvent event) {
        
        System.out.println(getFRequirement());
        Project currentProject = (Project)projectBean.getSelected();
        getFRequirement().setIdProject(currentProject);
        
        setSelected(getFRequirement());
        functionalRequirementService.create(getFRequirement());
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean isFRequirementType() {
        return functionalRequirementBean.getSelected() instanceof FunctionalRequirement;
    }

    public FunctionalRequirement getFRequirement() {
        return getSelected();
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