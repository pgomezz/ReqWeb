package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.AltranreqRole;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Privilege;
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

}
