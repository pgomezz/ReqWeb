package pt.altran.altranreq.manager.altranrequser;

import pt.altran.altranreq.entities.AltranreqUser;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.UserService;

@Named(value = "altranreqUserCreateController")
@ViewScoped
public class AltranreqCreateUserController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private UserService ejbService;
    
    private AltranreqUser altranreqUser;

    public AltranreqCreateUserController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
        super.setSelected(new AltranreqUser());
    }

    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getAltranreqUser());
        ejbService.create(getAltranreqUser());
        String successMsg = ResourceBundle.getBundle("/user").getString("Success_on_create");
        JsfUtil.addSuccessMessage(successMsg);
    }

    public AltranreqUser getAltranreqUser() {
        AltranreqUser sel = getSelected();
        return sel;
    }
    
    
}
