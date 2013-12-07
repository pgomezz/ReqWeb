/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.services;

import java.io.Serializable;
import java.util.Collection;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;

@Named
@ViewScoped
public class PrivilegeCollection implements Serializable {

    @Context
    private Collection<Privilege> AllPrivileges;

    @Context
    private Collection<Privilege> RolePrivileges;

    @Inject
    private PrivilegeService privilegeService;

    @Inject
    private AltranReqRoleService roleService;

    public void setAllPrivileges() {
        this.AllPrivileges = privilegeService.findAll();
    }

    public void setRolePrivileges(AltranreqRole role) {
        this.RolePrivileges = roleService.getRolePrivileges(role.getIdRole().intValue());
    }

    public Collection<Privilege> getAllPrivileges() {
        return privilegeService.findAll();
    }

    public void setPrivilege(Privilege p) {
        if (!RolePrivileges.contains(p)) {
            this.RolePrivileges.add(p);
        }
    }
    
    public boolean hasPrivilege(Privilege i){
        return (this.AllPrivileges.contains(i));
    }
    
    public Collection<Privilege> getPrivileges() {
        return this.RolePrivileges;
    }

    public void removePrivilege(Privilege p) {
        if (this.RolePrivileges.contains(p)) {
            this.RolePrivileges.remove(p);
        }
    }

}
