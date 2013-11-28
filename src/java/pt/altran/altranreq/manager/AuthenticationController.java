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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.services.AuthenticationBean;
import pt.altran.altranreq.services.AuthenticationService;
import pt.altran.altranreq.services.AuthorizationService;
import pt.altran.altranreq.services.AuthorizationServiceImp;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.loginBean;

/**
 *
 * @author User
 */

@Named(value = "authenticationController")
@ViewScoped

public class AuthenticationController implements Serializable {
    
    @Inject
    private ProjectService projectService;

    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private AuthenticationBean authenticationBean;
    
    @Inject
    private ProjectServiceBean projectServiceBean;
        
    @Inject
    private AuthorizationService authorizationService;

    private AltranreqUser user;
    
    private String username; 
    private String password; 
    private boolean isAdmin;

    @PostConstruct
    public void init(){
        System.out.println("Bem injectado");
    }
    
    public boolean isIsAdmin() {
        return authorizationService.isAdmin();
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
    
    public void login(ActionEvent event) {
        //RequestContext contextt = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
       // boolean loggedIn = false;
       
        user=authenticationService.login(username, password);
        
          if(user!=null)
      //  if (username.equals(u.getUsername()) && password.equals(u.getPassword())) //username.equals("bn") && password.equals("bn")
        { 
              authorizationService.setUserID(user.getIdUser().intValue());
              AuthorizationServiceImp authorizationServiceImp = (AuthorizationServiceImp)authorizationService;
              //Porque só o imp é que tem o set
              authorizationServiceImp.setIsAdmin(user.getIsAdmin()=='1');
              
            //loggedIn = true;
            //msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", username);
           // try {
               // loggedIn = true;
                  msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", authenticationBean.getUsername());
                  FacesContext context = FacesContext.getCurrentInstance();
                  context.addMessage("SUCESSO", msg);
                // this.originalURL = request.getContextPath() + "home.xhtml";
                authenticationBean.setUser(user);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
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
