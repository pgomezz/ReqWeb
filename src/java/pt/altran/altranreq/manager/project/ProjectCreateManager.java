/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.project;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.OrganizationService;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.UserService;

/**
 *
 * @author francisco
 */
@Named(value = "projectCreateManager")
@ViewScoped
public class ProjectCreateManager extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;
    
    @Inject
    private UserService userService;
    
    @Inject
    private OrganizationService organizationService;
    
    private AltranreqUser user;
    
    private Organization organization;

    @PostConstruct
    public void init() {
        super.setService(projectService);
        super.setSelected(new Project());
    }

    public String getState(int number) {
        return projectService.getProjectStateString(number);
    }

    public Project getProject() {
        Project sel = getSelected();
        return sel;
    }

    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getProject());
        projectService.create(getProject());
        String successMsg = ResourceBundle.getBundle("/project").getString("Success_on_create");
        JsfUtil.addSuccessMessage(successMsg);
    }

    public List<AltranreqUser> getUsersList() {
        List<AltranreqUser> users = userService.findAll();
        return users;
    }
    
    public List<Organization> getOrganizationList(){
        List<Organization> orgList = organizationService.findAll();
        return orgList;
    }
    
    public void setOrganization(Organization org){
        String id = (org==null) ? null : org.getIdOrganization().toString();
        getProject().setIdOrganization(org);
    }
    
    public Organization getOrganization(){
        return organization;
    }

    public void setProjectManager(AltranreqUser aru) {
        System.out.println("Aqui é o setProjetManager");
        BigInteger id = (aru==null) ? null : aru.getIdUser().toBigInteger();
        getProject().setIdProjectManager(id);
        user = aru;
    }

    public AltranreqUser getProjectManager() {
        return user;
    }
}
