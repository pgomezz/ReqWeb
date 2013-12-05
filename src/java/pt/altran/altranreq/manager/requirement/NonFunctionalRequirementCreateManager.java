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
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.FunctionalRequirementController;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFtServiceBean;
import pt.altran.altranreq.services.RNFunctionalFilter;
import pt.altran.altranreq.services.TreeService;

/**
 *
 * @author francisco
 */
@Named(value = "nonFunctionalRequirementCreateManager")
@ViewScoped
public class NonFunctionalRequirementCreateManager extends AbstractController<NonFunctionalRequirement> implements Serializable {

    @Inject
    private RNFService nonFunctionalRequirementService;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private RNFtServiceBean nonFunctionalRequirementBean;
    private NonFunctionalRequirement nonFunctionalRequirement;
   
    
    
    
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

    public NonFunctionalRequirementCreateManager() {
        super(NonFunctionalRequirement.class);
    }

    @PostConstruct
    public void init() {
        super.setService(nonFunctionalRequirementService);
        
        super.setSelected(new NonFunctionalRequirement());
        

    }

    public boolean isNonFunctionalRequirementType() {
        return treeService.getSelected() instanceof FunctionalRequirement;
    }

    public FunctionalRequirement getNonFunctionalRequirement() {
        return (FunctionalRequirement) treeService.getSelected();
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

    @Override
    public void saveNew(ActionEvent event) {
        
        System.out.println(getNFRequirement());
        Project currentProject = (Project)projectBean.getSelected();
        getNFRequirement().setIdProject(currentProject);
        
        setSelected(getNFRequirement());
        nonFunctionalRequirementService.create(getNFRequirement());
        
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public boolean isFRequirementType() {
        return nonFunctionalRequirementBean.getSelected() instanceof NonFunctionalRequirement;
    }

     public boolean isNFRequirementType()
    {
        return nonFunctionalRequirementBean.getSelected() instanceof NonFunctionalRequirement;
    }
    
    public NonFunctionalRequirement getNFRequirement()
    {
        
        //return (NonFunctionalRequirement)nonFunctionalRequirementBean.getSelected();
        return getSelected();
    }
    
    public void setNonFunctionalRequirement()
    {
        nonFunctionalRequirementBean.setSelected(this.getSelected());
    }
    
    
    public void redirect(int action) throws IOException
    {if (action == 1) {
            nonFunctionalRequirement = super.prepareCreate(null);
        } else {
            nonFunctionalRequirementBean.setSelected(this.getSelected());
        }
        
    }
    

   
}