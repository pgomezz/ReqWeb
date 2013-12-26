package pt.altran.altranreq.manager.altranreqrole;

import pt.altran.altranreq.entities.AltranreqRole;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.PrivilegeService;

@Named(value = "privilegewController")
@ViewScoped
public class PrivilegeController extends AbstractController<Privilege> implements Serializable {

    @Inject
    private PrivilegeService ejbService;
    
    private Privilege privilege;

    public PrivilegeController() {
        super(Privilege.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public List<Privilege> getItems() {
        return super.getItems(); //To change body of generated methods, choose Tools | Templates.
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
    /*public void keepState(){
        privilegeBean.setSelected(this.getSelected());
    }*/

}
