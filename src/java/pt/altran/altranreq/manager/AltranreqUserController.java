package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.AltranreqUser;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.AltranReqRoleService;
import pt.altran.altranreq.services.UserService;

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
