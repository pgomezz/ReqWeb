/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.services;

import java.io.Serializable;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class AltranreqRoleBean implements Serializable {

    @Context
    private AltranreqRole selected;

    public void setSelected(AltranreqRole selected) {
        this.selected = selected;
    }

    public AltranreqRole getSelected() {
        return selected;
    }
    public Collection<Privilege> getPrivileges(AltranreqRole r) {
        return r.getPrivilegeCollection();
    }    
}