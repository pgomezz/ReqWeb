/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.requirement;

import java.io.IOException;
import java.io.Serializable;
import java.util.ResourceBundle;
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
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.FunctionalRequirementController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;

/**
 *
 * @author francisco
 */
@Named(value = "functionalRequirementEditManager")
@ViewScoped
public class FunctionalRequirementEditManager extends AbstractController<FunctionalRequirement> implements Serializable {

   @Inject
    private FunctionalRequirementService  functionalService;
 
    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private FunctionalRequirementServiceBean functionalRequirementBean;
    
    private FunctionalRequirement functionalRequirement;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    

    @PostConstruct
    public void init() {
        super.setService(functionalService);
        
    }

    public String getState(int number) {
        return functionalService.getRequirementStateString(number);
    }

    public FunctionalRequirement getFunctionalRequirement() {
        FunctionalRequirement sel = getSelected();
        return sel;
    }

    @Override
    public void save(ActionEvent event) {

        FunctionalRequirement fr = (FunctionalRequirement) functionalRequirementBean.getSelected();
        setSelected(fr);
        functionalService.edit(fr);
        String successMsg = ResourceBundle.getBundle("MyBundle").getString("FunctionalRequirementUpdated");
        JsfUtil.addSuccessMessage(successMsg);


    }
    
    
    public boolean isFRequirementType() {
        return functionalRequirementBean.getSelected() instanceof FunctionalRequirement;
    }

    public FunctionalRequirement getFRequirement() {
        return (FunctionalRequirement) functionalRequirementBean.getSelected();
    }

    public void setFunctionalRequirement() {
        functionalRequirementBean.setSelected(this.getSelected());
    }
}