/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.organization;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.OrganizationBean;
import pt.altran.altranreq.services.OrganizationService;

/**
 *
 * @author User
 */
@Named(value = "organizationEditManager")
@ViewScoped
public class OrganizationEditManager extends AbstractController<Organization> implements Serializable {

    @Inject
    private OrganizationBean organizationBean;
    
    @Inject
    private OrganizationService organizationService;
    
    private Organization organization;

    @PostConstruct
    public void init() {
        super.setService(organizationService);
    }
    
    @Override
    public void save(ActionEvent event) {
        organizationService.edit(getOrganization());
    }
    
    public Organization getOrganization() {
        return (Organization) organizationBean.getSelected();
    }
    
       
}
