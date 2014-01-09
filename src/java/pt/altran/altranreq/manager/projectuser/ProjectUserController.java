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

        ProjectUserPK PK = new ProjectUserPK();
        PK.setIdProject(projectServiceBean.getProject().getIdProject());
        PK.setIdUser(this.altranreqUser.getIdUser());
        getProjectUser().setProjectUserPK(PK);
        projectUserService.create(getSelected());
    }

    @Override
    public void delete(ActionEvent event) {
        
        projectUserService.remove(projectUserService.getProjectUser(projectServiceBean.getProject().getIdProject(), this.altranreqUser.getIdUser()));
    }

    public ProjectUser getProjectUser() {
        ProjectUser sel = getSelected();
        return sel;
    }

    public void setAltranreqUser(AltranreqUser user) {
        altranreqUser = user;
    }

    public AltranreqUser getAltranreqUser() {
        return altranreqUser;
    }
}
