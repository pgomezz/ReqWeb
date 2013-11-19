package backingBeans;

import entities.AltranreqUser;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.AltranReqRoleService;
import sessionBeans.UserService;

@Named(value = "altranreqUserController")
@ViewScoped
public class AltranreqUserController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private UserService ejbService;

    public AltranreqUserController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
