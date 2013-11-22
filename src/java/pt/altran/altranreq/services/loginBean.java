package pt.altran.altranreq.services;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import pt.altran.altranreq.entities.AltranreqUser;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Project;

//@ManagedBean(name = "loginBean")
@Named
@SessionScoped
//@Stateless
public class loginBean implements Serializable {

    private AltranreqUser user;
    private String username;
    private String password;
    private boolean valid;
    private Project project;
    private AltranreqRole role;
    String originalURL = "faces/index.xhtml";
    String original = "faces/login.xhtml";

    public loginBean() {
        this.user = null;
        this.username = null;
        this.password = null;
        this.valid = false;
        this.project = null;
        this.role = null;
    }


    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AltranreqRole getRole() {
        return role;
    }

    public void setRole(AltranreqRole role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //@WebMethod
    public AltranreqUser getUser() {
        return null;
    }

    //  @WebMethod  
    public void login(ActionEvent e) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        /*if(username.equals(null))
         {          
         msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Deve inserir um Username e Password válido."); 
         }*/
        if (valid) { //username.equals("bb") && password.equals("bb")
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", username);
            try {
                //   this.originalURL = request.getContextPath() + "home.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }
            //   FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
            // originalURL="faces/index.xhtml";
            System.out.println("Login Sucess");
            //return new AltranreqUser();
            //  return true;
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos.");
            /*try {
             msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos."); 
             //   this.originalURL = request.getContextPath() + "home.xhtml";
             FacesContext.getCurrentInstance().getExternalContext().redirect(original);
             } catch (IOException ex) {
             Logger.getLogger(AuthenticationServiceImp.class.getName()).log(Level.SEVERE, null, ex);
             }*/
            //  username.equals("");
            // password.equals("");
            System.out.println("Login Failed");
            // return false;
            //  return null;
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        //  context.addCallbackParam("originalURL", originalURL);
        //System.out.println(""+em.createQuery("AltranreqUser.findByUsername").getFirstResult());

    }

    public boolean isValid() {
        AuthenticationService s = null;
        this.user = s.Login(this.username, this.password);
        if (this.user == null) {
            this.valid = false;
        } else {
            this.valid = true;
        }
        return this.valid;
    }

    public void closeProject() {
        this.project = null;
        this.role = null;
    }

    public void logout() {
        this.user=null;
        this.username=null;
        this.password=null;
    }

    public boolean isAdmin() {
        AuthenticationService s = null;
        return s.isAdmin(this.user);
    }

}
