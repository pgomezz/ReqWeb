/*
 * To change this license header, choose License Headers in Organization Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.organization;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.OrganizationService;

/**
 *
 * @author francisco
 */
@Named(value = "organizationCreateManager")
@ViewScoped
public class OrganizationCreateManager extends AbstractController<Organization> implements Serializable {
    
    @Inject
    private OrganizationService organizationService;

    @PostConstruct
    public void init() {
        super.setService(organizationService);
        super.setSelected(new Organization());
    }
    
    @Override
    public void saveNew(ActionEvent event){
        setSelected(getOrganization());
        organizationService.create(getOrganization());
    
        String successMsg = ResourceBundle.getBundle("/organization").getString("Success_on_create");
        JsfUtil.addSuccessMessage(successMsg);
    
    }

    public Organization getOrganization() {
        Organization sel = getSelected();
        return sel;
    }
}