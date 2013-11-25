package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.Project;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.ProjectFilter;
import pt.altran.altranreq.services.ProjectService;

@Named(value = "projectController")
@ViewScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;
    
    
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

    public String getProjectManagerName(Integer idProjectManager){
        if (idProjectManager==null) {
            return ResourceBundle.getBundle("/project").getString("WithoutProjecManager");
        }
        return projectService.getProjectUserName(idProjectManager);
    }

    @Override
    public void saveNew(ActionEvent event) {
        super.prepareCreate(event);
        super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Project> getProjectsbyUser(){
        ProjectFilter projectFilter = new ProjectFilter();
        projectFilter.setUser(1);
        return projectService.findProjectByFilter(projectFilter);
    }
    
    
}
