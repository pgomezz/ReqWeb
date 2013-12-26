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
import pt.altran.altranreq.entities.Client;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class ClientBean implements Serializable {

    @Context
    private Client selected;

    public void setSelected(Client selected) {
        this.selected = selected;
    }

    public Client getSelected() {
        return selected;
    }
}