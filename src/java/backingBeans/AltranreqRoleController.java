package backingBeans;

import entities.AltranreqRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.AltranReqRoleService;

@Named(value = "altranreqRoleController")
@ViewScoped
public class AltranreqRoleController extends AbstractController<AltranreqRole> implements Serializable {

    @Inject
    private AltranReqRoleService ejbService;

    public AltranreqRoleController() {
        super(AltranreqRole.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
