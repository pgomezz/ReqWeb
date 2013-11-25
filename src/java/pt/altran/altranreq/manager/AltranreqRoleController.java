package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.AltranreqRole;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.AltranReqRoleService;

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
        super.setService(ejbService);
    }

}
