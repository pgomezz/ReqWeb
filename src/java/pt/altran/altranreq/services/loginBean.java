package pt.altran.altranreq.services;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import pt.altran.altranreq.entities.AltranreqUser;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;
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
    RequestContext context;
    HttpSession session;
    String originalURL = "faces/index.xhtml";
    String original = "faces/login.xhtml";

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    //private Object isAdmin;

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


    public String login() {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
          AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsername").
                setParameter("username", username).
                getSingleResult();
     
        if (username.equals(u.getUsername()) && password.equals(u.getPassword())){ //username.equals("bb") && password.equals("bb")
            loggedIn = true;
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", username);
            try {
                //   this.originalURL = request.getContextPath() + "home.xhtml";
                FacesContext.getCurrentInstance().getExternalContext().redirect(originalURL);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticationServiceImp.class.getName()).log(Level.SEVERE, null, ex);
            }      
            System.out.println("Login Sucess");       
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos.");
            System.out.println("Login Failed");
             return "fail";
        }
        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        return "success";
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
        this.user = null;
        this.username = null;
        this.password = null;
        this.session = null;
        this.context = null;

    }

    public BigDecimal getUserID()
    {
       RequestContext context = RequestContext.getCurrentInstance();
      // ExternalContext a;
      // a.
        FacesMessage msg = null;
        boolean loggedIn = false;
          AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsername").
                setParameter("username", username).
                getSingleResult(); 
          return u.getIdUser();
    }
    
   public String getNameUser()
    {
       RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
          AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsername").
                setParameter("username", username).
                getSingleResult(); 
          return u.getName();
    }
    
   public char getIsAdmin()
    {
       RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false;
        AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsername").
                setParameter("username", username).
                getSingleResult(); 
        return u.getIsAdmin();
    }
   
   
    public boolean isAdmin() {
        this.context = RequestContext.getCurrentInstance();

        AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsername").
                setParameter("username", username).
                getSingleResult();

        if (u.getIsAdmin() == '1') {
            return true;
        } else if (u.getIsAdmin() == '0') {
            return false;
        }
        //  AuthenticationService s = null;
        // return s.isAdmin(this.user);
        return false;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.
                getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public String getUserIdBN() {
        this.session = getSession();
        if (session != null) {
            return (String) session.getAttribute("userid");
        } else {
            return null;
        }
    }
   
}
