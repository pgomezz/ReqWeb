package backingBeans;

import entities.Project;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.ProjectService;

@Named(value = "projectController")
@ViewScoped
public class ProjectController extends AbstractController<Project> implements Serializable {

    @Inject
    private ProjectService ejbService;

    public ProjectController() {
        super(Project.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
