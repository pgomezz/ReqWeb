package backingBeans;

import entities.Privilege;
import sessionBeans.PrivilegeFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "privilegeController")
@ViewScoped
public class PrivilegeController extends AbstractController<Privilege> implements Serializable {

    @Inject
    private PrivilegeFacade ejbFacade;

    public PrivilegeController() {
        super(Privilege.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
