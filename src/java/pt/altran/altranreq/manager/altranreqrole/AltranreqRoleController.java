package pt.altran.altranreq.manager.altranreqrole;

import pt.altran.altranreq.entities.AltranreqRole;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.AltranReqRoleService;
import pt.altran.altranreq.services.AltranreqRoleBean;

@Named(value = "altranreqRoleController")
@ViewScoped
public class AltranreqRoleController extends AbstractController<AltranreqRole> implements Serializable {

    @Inject
    private AltranReqRoleService ejbService;
    
    /*@Inject
    private PrivilegeService privilegeService;*/
    
    @Inject
    private AltranreqRoleBean roleBean;
    
    private AltranreqRole altranreqRole;
    //private Privilege privilege;

    public AltranreqRoleController() {
        super(AltranreqRole.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    public AltranreqRole getAltranreqRole() {
        return altranreqRole;
    }

    public void setAltranreqRole(AltranreqRole altranreqRole) {
        this.altranreqRole = altranreqRole;
    }  

    public AltranreqRole setPrivileges(AltranreqRole role, Collection<Privilege> col) {
        //Lê os prvlégios do perfil activo
        Collection<Privilege> old_col = role.getPrivilegeCollection();
        //Remove os privilegios que não existem no novo perfil
        for (Privilege p : old_col) {
            if (!col.contains(p)) {
                old_col.remove(p);
            }
        }
        //Adiciona os privilegios 
        for (Privilege p : col) {
            if (!old_col.contains(p)) {
                old_col.add(p);
            }
        }
        role.setPrivilegeCollection(old_col);
        return role;
    }

    /*public Privilege getPrivilege() {
        System.out.println("AQUI");
        return privilege;
    }*/
    
    public Collection<Privilege> getPrivileges(AltranreqRole r) {
        return r.getPrivilegeCollection();
    }
    
    public void keepState(){
        roleBean.setSelected(this.getSelected());
    }

}
