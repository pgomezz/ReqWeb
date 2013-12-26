/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.Organization;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class OrganizationBean implements Serializable {

    @Context
    private Organization selected;

    public void setSelected(Organization selected) {
        this.selected = selected;
    }

    public Organization getSelected() {
        return selected;
    }
}