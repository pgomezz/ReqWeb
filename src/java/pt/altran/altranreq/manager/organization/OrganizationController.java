/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.organization;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.OrganizationService;
import pt.altran.altranreq.services.OrganizationBean;

/**
 *
 * @author User
 */
@Named(value = "organizationController")
@ViewScoped
public class OrganizationController extends AbstractController<Organization> implements Serializable {

    @Inject
    private OrganizationService organizationService;

    @Inject
    private OrganizationBean organizationBean;

    private Organization organization;

    @PostConstruct
    public void init() {
        super.setService(organizationService);
    }

    public OrganizationService getOrganizationService() {
        return organizationService;
    }

    public void setOrganizationService(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    public Organization getOrganization() {
        Organization sel = getSelected();
        return sel;
    }

    public void keepState() throws IOException {
        organizationBean.setSelected(this.getSelected());
    }

    @Override
    public void delete(ActionEvent event) {
        try {
            organizationService.remove(this.getSelected());
            super.setItems(organizationService.findAll());
            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/organization").getString("RemoveSuccessMessage"), null);
        } catch (Exception e) {
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/organization").getString("ErrorMessage"), null);
        }
    }

    public void sendMessages(FacesMessage.Severity severity, String summary, String details) {
        FacesMessage message = new FacesMessage(severity, summary, details);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
