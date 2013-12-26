package pt.altran.altranreq.manager.altranrequser;

import pt.altran.altranreq.entities.AltranreqUser;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.AltranreqUserBean;
import pt.altran.altranreq.services.UserService;

@Named(value = "altranreqUserEditController")
@ViewScoped
public class AltranreqUserEditController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private UserService ejbService;
    
    @Inject
    private AltranreqUserBean altranreqUserBean;
    
    private AltranreqUser altranreqUser;

    public AltranreqUserEditController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public void save(ActionEvent event) {
        ejbService.edit(getAltranreqUser());
    }
    
    public AltranreqUser getAltranreqUser() {
        return (AltranreqUser) altranreqUserBean.getSelected();
    }
}
