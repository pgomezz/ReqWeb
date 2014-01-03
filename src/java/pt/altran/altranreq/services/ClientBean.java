/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.Client;
import pt.altran.altranreq.entities.Organization;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class ClientBean implements Serializable {

    @Context
    private Client selected;
    
    @Inject
    private OrganizationService organizationService;
    
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public void setSelected(Client selected) {
        this.selected = selected;
    }

    public Client getSelected() {
        return selected;
    }
    
    public String getIdOrganization(){
        return selected.getIdOrganization();
    }
    
    public Organization getOrganization(){
        return organizationService.getOrganizationById(selected.getIdOrganization());
    }
    
    public void setIdOrganization(String id){
        selected.setIdOrganization(id);
    }
}