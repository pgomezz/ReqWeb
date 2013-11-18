package backingBeans;

import entities.ProjectUser;
import sessionBeans.ProjectUserFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "projectUserController")
@ViewScoped
public class ProjectUserController extends AbstractController<ProjectUser> implements Serializable {

    @Inject
    private ProjectUserFacade ejbFacade;

    public ProjectUserController() {
        super(ProjectUser.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

    @Override
    protected void setEmbeddableKeys() {
        this.getSelected().getProjectUserPK().setIdProject(this.getSelected().getProject().getIdProject());
        this.getSelected().getProjectUserPK().setIdUser(this.getSelected().getAltranreqUser().getIdUser());
    }

    @Override
    protected void initializeEmbeddableKey() {
        this.getSelected().setProjectUserPK(new entities.ProjectUserPK());
    }

}
