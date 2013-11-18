package backingBeans;

import entities.AltranreqRole;
import sessionBeans.AltranreqRoleFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "altranreqRoleController")
@ViewScoped
public class AltranreqRoleController extends AbstractController<AltranreqRole> implements Serializable {

    @Inject
    private AltranreqRoleFacade ejbFacade;

    public AltranreqRoleController() {
        super(AltranreqRole.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
