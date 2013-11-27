package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.Project;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.services.ProjectFilter;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.UserService;

@Named(value = "projectController")
@RequestScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService projectService;
    @Inject 
    private UserService userService;
    private AltranreqUser user;
    
    @Inject
    private ProjectServiceBean projectServiceBean;
    

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
        //setSelected(project);
        try{
        Project pj = (Project)projectServiceBean.getSelected();
        setSelected(pj);
        System.out.println(pj.getBeginDate() + " - " + pj.getIdProject() + " - " + pj.getIdOrganization()+ " - " + pj.getIdProjectManager() + " - " + pj.getProjectState());
        projectService.edit(pj); //To change body of generated methods, choose Tools | Templates.
        
            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/project").getString("EditSuccessMessage"), null);
        }
        catch(Exception e){
 
          sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("ErrorMessage"), null );
            
        }
    }
        public void sendMessages(FacesMessage.Severity severity,String summary,String details){
         
        FacesMessage message = new FacesMessage(severity, summary, details);

         FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
        @Override
    public void delete(ActionEvent event) {
           try{
        
        System.out.println("passei acckiasoidasfgshlgçadhfhs");
        Project pj = (Project)projectServiceBean.getSelected();
        setSelected(pj);
        projectService.remove(pj); //To change body of generated methods, choose Tools | Templates.
        sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("RemoveSuccessMessage"), null);
                }catch(Exception e){
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
        project = super.prepareCreate(null);
    }
    
    public String getState(int number) {
        return projectService.getProjectStateString(number);
    }

    public String getProjectManagerName(Integer idProjectManager) {
        if (idProjectManager == null) {
            return ResourceBundle.getBundle("/project").getString("WithoutProjecManager");
        }
        return projectService.getProjectUserName(idProjectManager);
    }

    public void setTerminology(String terminology){
        project.setTerminology(terminology);
    }
    
    public String getTerminology(){
        return project.getTerminology();
    }
 
    public void setProjectManager(AltranreqUser aru){
        user= aru;
    }
    public AltranreqUser getProjectManager(){
        System.out.println("tentou apanhar");
        return user;
    }
    
    @Override
    public void saveNew(ActionEvent event) {
        try{
        project.setIdProjectManager(user.getIdUser().toBigInteger());
        setSelected(project);
        projectService.create(project);
        sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/project").getString("CreateSuccessMessage"), null);
                }catch(Exception e){
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/project").getString("ErrorMessage"), null);
        }
    }
    
    public List<Project> getProjectsbyUser(){
        ProjectFilter projectFilter = new ProjectFilter();
        projectFilter.setUser(1);
        return projectService.findProjectByFilter(projectFilter);
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
    
    public void redirect() throws IOException
    {
        projectServiceBean.setSelected(this.getSelected());
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        
//        if (option == 1)
//            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/View.xhtml");
//        if (option == 2)
//            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/Edit.xhtml");
    }
}
