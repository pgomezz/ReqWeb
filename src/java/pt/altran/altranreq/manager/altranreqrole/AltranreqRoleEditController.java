package pt.altran.altranreq.manager.altranreqrole;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.AltranReqRoleService;
import pt.altran.altranreq.services.AltranreqRoleBean;

@Named(value = "altranreqRoleEditController")
@ViewScoped
public class AltranreqRoleEditController extends AbstractController<AltranreqRole> implements Serializable {

    @Inject
    private AltranReqRoleService ejbService;
    
    @Inject
    private AltranreqRoleBean altranreqRoleBean;
    
    private AltranreqRole altranreqRole;

    public AltranreqRoleEditController() {
        super(AltranreqRole.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public void save(ActionEvent event) {
        ejbService.edit(getAltranreqRole());
    }
    
    public AltranreqRole getAltranreqRole() {
        return (AltranreqRole) altranreqRoleBean.getSelected();
    }
}
