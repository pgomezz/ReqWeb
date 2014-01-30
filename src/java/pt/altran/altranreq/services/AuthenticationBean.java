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
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.AltranreqUser;

/**
 *
 * @author User
 */

//@ManagedBean(name = "loginBean")
@Named
@SessionScoped

public class AuthenticationBean implements Serializable{
    
    @Context 
    private AltranreqUser user;
    
    @Context 
    private AltranreqRole role;
    
    @Context 
    private String username; 
    
    @Context 
    private String password; 

    public String getUsername() {
        //System.out.println("USERNAME"+username);
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AltranreqRole getRole() {
        return role;
    }

    public void setRole(AltranreqRole role) {
        this.role = role;
    }

    public AltranreqUser getUser() {
        return user;
    }

    public void setUser(AltranreqUser user) {
        this.user = user;
    }
    
}
