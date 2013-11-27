package pt.altran.altranreq.services;

import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;

@Named
@SessionScoped
public class AltranreqRoleServiceBean implements Serializable {

    @Context
    private Object selected;

    @Context
    private AltranreqRole role;

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getSelected() {
        return selected;
    }

    public Collection<Privilege> getPrivileges(AltranreqRole r) {
        return r.getPrivilegeCollection();
    }
}
