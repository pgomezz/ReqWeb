package pt.altran.altranreq.manager.altranreqrole;



import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.AltranReqRoleService;

@Named(value = "altranreqRoleCreateController")
@ViewScoped
public class AltranreqCreateRoleController extends AbstractController<AltranreqRole> implements Serializable {

    @Inject
    private AltranReqRoleService ejbService;
    
    private AltranreqRole altranreqRole;

    public AltranreqCreateRoleController() {
        super(AltranreqRole.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
        super.setSelected(new AltranreqRole());
    }

    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getAltranreqRole());
        ejbService.create(getAltranreqRole());
        String successMsg = ResourceBundle.getBundle("/role").getString("Success_on_create");
        JsfUtil.addSuccessMessage(successMsg);
    }

    public AltranreqRole getAltranreqRole() {
        AltranreqRole sel = getSelected();
        return sel;
    }
}
