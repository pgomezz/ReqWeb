/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.project;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.UserService;

/**
 *
 * @author francisco
 */
@Named(value = "projectEditManager")
@ViewScoped
public class ProjectEditManager extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;
    @Inject
    private ProjectServiceBean projectServiceBean;
    @Inject
    private UserService userService;
    private AltranreqUser user;
    private List<AltranreqUser> usersList;

    @PostConstruct
    public void init() {
        super.setService(projectService);
    }

    public String getState(int number) {
        return projectService.getProjectStateString(number);
    }

    public Project getProject() {
        return (Project) projectServiceBean.getSelected();
    }

    @Override
    public void save(ActionEvent event) {
   
        projectService.edit(getProject());
        String successMsg = ResourceBundle.getBundle("/project").getString("Success_on_update");
        JsfUtil.addSuccessMessage(successMsg);
    }

    public List<AltranreqUser> getUsersList() {
        if (usersList == null) {
            usersList = userService.findAll();
        }
        return usersList;
    }

    public void setProjectManager(AltranreqUser aru) {
        getProject().setIdProjectManager(aru.getIdUser().toBigInteger());
        user = aru;
    }

    public AltranreqUser getProjectManager() {
        if(getProject().getIdProjectManager()==null)
            return null;
        if (user==null) {
            for (AltranreqUser userFound:usersList) {
                if (getProject().getIdProjectManager().intValue() == userFound.getIdUser().intValue()) {
                    user = userFound;
                    return user;
                }
            }
        }
        return user;
    }
}
