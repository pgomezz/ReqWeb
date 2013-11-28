/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.manager;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.services.AuthenticationBean;
import pt.altran.altranreq.services.AuthenticationService;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.loginBean;

/**
 *
 * @author User
 */

@Named(value = "authenticationController")
@SessionScoped

public class AuthenticationController implements Serializable {
    
    @Inject
    private ProjectService projectService;

    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private AuthenticationBean authenticationBean;
    
    @Inject
    private ProjectServiceBean projectServiceBean;
        
    private AltranreqUser user;
    
    private String username; 
    private String password; 
    private boolean isAdmin;

    public boolean isIsAdmin() {
        return authenticationService.isAdmin(authenticationBean.getUser());
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
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

    public AltranreqUser getUser() {
        return user;
    }

    public void setUser(AltranreqUser user) {
        this.user = user;
    }
    
    public void login() {
        //RequestContext contextt = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
       // boolean loggedIn = false;
       
        user=authenticationService.Login(username, password);
          if(user!=null)
      //  if (username.equals(u.getUsername()) && password.equals(u.getPassword())) //username.equals("bn") && password.equals("bn")
        { 
            //loggedIn = true;
            //msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", username);
           // try {
               // loggedIn = true;
                  msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", authenticationBean.getUsername());
                  FacesContext context = FacesContext.getCurrentInstance();
                  context.addMessage("SUCESSO", msg);
            try {
                // this.originalURL = request.getContextPath() + "home.xhtml";
                authenticationBean.setUser(user);
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(loginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.println("Login Sucess");   
           // } catch (IOException ex) {
               // Logger.getLogger(AuthenticationServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            //}                 
        } 
        else {
            //loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos.");
           // FacesMessage message = new FacesMessage("Invalid password length");
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("ERROR", msg);
            
            System.out.println("Login Failed");
           //  return "fail";
        }
        //FacesContext.getCurrentInstance().addMessage(null, msg);
       // context.addCallbackParam("loggedIn", loggedIn);
       // return "sucess";
    }
}
