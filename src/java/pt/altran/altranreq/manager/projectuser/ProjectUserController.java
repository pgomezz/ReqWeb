package pt.altran.altranreq.manager.projectuser;

import pt.altran.altranreq.entities.ProjectUser;
import pt.altran.altranreq.services.ProjectUserService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUserPK;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;

@Named("projectUserController")
@SessionScoped
public class ProjectUserController extends AbstractController<ProjectUser> implements Serializable {


    @Inject
    private ProjectUserService projectUserService;
    
    @Inject
    private ProjectServiceBean projectServiceBean;
    
    @Inject
    private ProjectService projectService;
    
    private ProjectUser projectUser;
    
    private Project project;
    
    private AltranreqUser altranreqUser;

    public ProjectUserController() {
        super.setService(projectUserService);
        super.setSelected(new ProjectUser());
    }

    @Override
    public void saveNew(ActionEvent event) {
        
       
        //setSelected(getSelected());
        //projectUser.getProjectUserPK().setIdUser(projectUser.getAltranreqUser().getIdUser());
        //System.out.println("GETIDUSERPK: "+projectUser.getProjectUserPK().getIdUser());
        //projectUser.getProjectUserPK().setIdProject(projectServiceBean.getIdProject());
        //System.out.println("GETIDPROJECTPK: "+projectUser.getProjectUserPK().getIdProject());
        //projectUser = new ProjectUser();
        //projectUser.setProjectUserPK(new ProjectUserPK());
        //setSelected(projectUser);
        
        ProjectUserPK PK = new ProjectUserPK();
        System.out.println("PROJECT SERV BEAN ID PROJ: "+projectServiceBean.getProject().getIdProject());
        PK.setIdProject(projectServiceBean.getProject().getIdProject());
//        System.out.println("IDUSER do GETSELECTED: "+getSelected().getAltranreqUser().getIdUser());
 //       PK.setIdUser(getProjectUser().getAltranreqUser().getIdUser());
        
        //System.out.println("PROJECTSERVICE Name"+projectService.find(id).getName());
        System.out.println("IDUSER: "+this.getAltranreqUser().getUsername());
        getProjectUser().setProjectUserPK(PK);
        getProjectUser().setAltranreqUser(this.getAltranreqUser());
        projectUserService.create(getSelected());
        //super.saveNew(event); //To change body of generated methods, choose Tools | Templates.
    }
    
    public ProjectUser getProjectUser(){
        ProjectUser sel = getSelected();
        return sel;
    }
    
    public void setAltranreqUser(AltranreqUser user) {
        
        altranreqUser = user;
    }
    
    public AltranreqUser getAltranreqUser() {
        return altranreqUser;
    }

    /*public String create() {
        try {
            projectUser.getProjectUserPK().setIdUser(projectUser.getAltranreqUser().getIdUser());
            projectUser.getProjectUserPK().setIdProject(projectUser.getProject().getIdProject());
            //getFacade().create(projectUser);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProjectUserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }*/

}
