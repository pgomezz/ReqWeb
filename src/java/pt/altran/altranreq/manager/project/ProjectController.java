package pt.altran.altranreq.manager.project;

import java.io.IOException;
import pt.altran.altranreq.entities.Project;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.AuthorizationService;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.UserService;

@Named(value = "projectController")
@ViewScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;

    @Inject
    private UserService userService;

    @Inject
    private AuthorizationService authService;

    @Inject
    private ProjectServiceBean projectServiceBean;

    private AltranreqUser user;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private Project project;

    public AltranreqUser getUser() {
        return user;
    }

    public void setUser(AltranreqUser user) {
        this.user = user;
    }

    @Override
    public void save(ActionEvent event) {
        try {
            Project pj = (Project) projectServiceBean.getSelected();
            setSelected(pj);
            projectService.edit(pj); //To change body of generated methods, choose Tools | Templates.

            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/project").getString("EditSuccessMessage"), null);
        } catch (Exception e) {
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("ErrorMessage"), null);
        }
    }

    public void sendMessages(FacesMessage.Severity severity, String summary, String details) {
        FacesMessage message = new FacesMessage(severity, summary, details);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    @Override
    public void delete(ActionEvent event) {
        try {
            projectService.remove(this.getSelected());
            super.setItems(projectService.findAll());
            //Project pj = (Project) projectServiceBean.getSelected();
            //setSelected(pj); //Experimentar sem isto quando a pagina estiver a funcionar
            //projectService.remove(pj); //To change body of generated methods, choose Tools | Templates.
            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/project").getString("RemoveSuccessMessage"), null);
        } catch (Exception e) {
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("ErrorMessage"), null);
        }
    }

    public List<AltranreqUser> getUsersList() {
        List<AltranreqUser> users = userService.findAll();
        return users;
    }

    public ProjectService getProjectService() {
        return projectService;
    }

    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public ProjectController() {
        super(Project.class);
    }

    @PostConstruct
    public void init() {
        super.setService(projectService);
        
    }

    public String getState(int number) {
        return projectService.getProjectStateString(number);
    }

    


    

   /* public FunctionalRequirement getFRequirement() {
       return (projectService)projectService.getSelected();
    }

    public void setFunctionalRequirement() {
        projectService.setSelected(this.getSelected());
    }*/
    
   /* public String getStateString(String state) {
        return projectService.getProjectStateStringValue(state);
    }*/
    
    public String getProjectManagerName(Integer idProjectManager) {
        if (idProjectManager == null) {
            return ResourceBundle.getBundle("/project").getString("WithoutProjecManager");
        }
        return projectService.getProjectUserName(idProjectManager);
    }

    public void setTerminology(String terminology) {
        project.setTerminology(terminology);
    }

    public String getTerminology() {
        return project.getTerminology();
    }

    public void setProjectManager(AltranreqUser aru) {
        user = aru;
    }

    public AltranreqUser getProjectManager() {
        return user;
    }

    @Override
    public void saveNew(ActionEvent event) {
        try {
            project.setIdProjectManager(user.getIdUser().toBigInteger());
            setSelected(project);
            projectService.create(project);
            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/project").getString("CreateSuccessMessage"), null);
        } catch (Exception e) {
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("ErrorMessage"), null);
        }
    }

    public List<Project> getProjectsbyUser() {
        if (authService.isAdmin()) {
            return projectService.findAll();
        }
        return authService.getProjectsFromUser();
    }

    public boolean isProjectType() {
        return projectServiceBean.getSelected() instanceof Project;
    }

    public Project getProject() {
        return (Project) projectServiceBean.getSelected();
    }

    public void setProject() {
        projectServiceBean.setSelected(this.getSelected());
    }
    
    public void redirect(int action) throws IOException {
        if (action == 1) {
            project = super.prepareCreate(null);
        } else {
            projectServiceBean.setSelected(this.getSelected());
        }
    }
    
}
