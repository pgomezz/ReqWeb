package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.Project;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.TreeService;

@Named(value = "projectController")
@ViewScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;
    
    @Inject
    private ProjectServiceBean projectServiceBean;
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    
    
    public boolean isProjectType()
    {
        return projectServiceBean.getSelected() instanceof Project;
    }
    
    public Project getProject()
    {
        return (Project)projectServiceBean.getSelected();
    }
    
    public void setProject()
    {
        projectServiceBean.setSelected(this.getSelected());
    }
    
    
    public void redirect(int option) throws IOException
    {
        projectServiceBean.setSelected(this.getSelected());
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        
        if (option == 1)
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/View.xhtml");
        if (option == 2)
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/Edit.xhtml");
    }
    
    
}
