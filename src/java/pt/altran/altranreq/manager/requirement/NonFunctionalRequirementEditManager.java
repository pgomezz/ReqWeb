/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.requirement;

import java.io.IOException;
import java.io.Serializable;
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
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.FunctionalRequirementController;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFtServiceBean;
import pt.altran.altranreq.services.TreeService;

/**
 *
 * @author francisco
 */
@Named(value = "nonFunctionalRequirementEditManager")
@ViewScoped
public class NonFunctionalRequirementEditManager extends AbstractController<NonFunctionalRequirement> implements Serializable {

   @Inject
    private RNFService nonFunctionalRequirementService;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private RNFtServiceBean nonFunctionalRequirementBean;
    private NonFunctionalRequirement nonFunctionalRequirement;
    
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
        
    }

    public String getState(int number) {
        return nonFunctionalRequirementService.getRequirementStateString(number);
    }

    public NonFunctionalRequirement getNonFunctionalRequirement() {
        NonFunctionalRequirement sel = getSelected();
        return sel;
    }

    @Override
    public void save(ActionEvent event) {

        NonFunctionalRequirement fr = (NonFunctionalRequirement) nonFunctionalRequirementBean.getSelected();
        setSelected(fr);
        nonFunctionalRequirementService.edit(fr);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FunctionalRequirementController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public boolean isNFRequirementType() {
        return nonFunctionalRequirementBean.getSelected() instanceof NonFunctionalRequirement;
    }

    public NonFunctionalRequirement getNFRequirement() {
        return (NonFunctionalRequirement) nonFunctionalRequirementBean.getSelected();
    }

    public void setNFunctionalRequirement() {
        nonFunctionalRequirementBean.setSelected(this.getSelected());
    }
}