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

@Named(value = "authenticationController")
@ViewScoped

//AuthenticationController  
public class AuthenticationController implements Serializable {
    
    @Inject
    private AuthenticationService authenticationService;
    
    @Inject
    private AuthenticationBean authenticationBean;
        
    @Inject
    private AuthorizationService authorizationService;

    private AltranreqUser user;
    
    private String username; 
    private String password; 
    private boolean isAdmin;

    @PostConstruct
    public void init(){
        //System.out.println("Bem injectado");
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
    
    //Verify username e password 
    public void login(ActionEvent event) {
        FacesMessage msg = null;
        user=authenticationService.login(username, password);
        authenticationBean.setUsername(username);
        if(user!=null)
        { 
              authorizationService.setUserID(user.getIdUser().intValue());
              AuthorizationServiceImp authorizationServiceImp = (AuthorizationServiceImp)authorizationService;
             
              authorizationServiceImp.setIsAdmin(user.getIsAdmin()=='1');
              
            
                  msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", authenticationBean.getUsername());
                  FacesContext context = FacesContext.getCurrentInstance();
                  context.addMessage("SUCESSO", msg);
                
                authenticationBean.setUser(user);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/indexProj.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos.");

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("ERROR", msg);
            
            System.out.println("Login Failed");

        }
   
    }
}
