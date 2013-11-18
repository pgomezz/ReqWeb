package backingBeans;

import entities.Project;
import sessionBeans.ProjectFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "projectController")
@ViewScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectFacade ejbFacade;

    public ProjectController() {
        super(Project.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
