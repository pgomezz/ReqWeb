package pt.altran.altranreq.services.authentication;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.manager.AuthenticationController;
import pt.altran.altranreq.services.AuthenticationBean;
import pt.altran.altranreq.services.AuthenticationService;
import pt.altran.altranreq.services.AuthorizationService;
import pt.altran.altranreq.services.AuthorizationServiceImp;

/**
 *
 * @author User
 */
@ManagedBean("authBean")
@Named(value = "authBean")
@SessionScoped

public class AuthBean implements Serializable {

    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private AuthenticationBean authenticationBean;
    @Inject
    private AuthorizationService authorizationService;

    @Context
    private AltranreqUser user;

    @Context
    private AltranreqRole role;

    @Context
    private String username;

    @Context
    private String password;

    public String getUsername() {
        System.out.println("USERNAME" + username);
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

    public String loginUser() {
        FacesMessage msg;
        user = authenticationService.login(username, password);
        authenticationBean.setUsername(username);
        System.out.println("Entrei pelo o login novo " + username);
        if (user != null) {
            authorizationService.setUserID(user.getIdUser().intValue());
            AuthorizationServiceImp authorizationServiceImp = (AuthorizationServiceImp) authorizationService;

            authorizationServiceImp.setIsAdmin(user.getIsAdmin() == '1');

            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bem-vindo", authenticationBean.getUsername());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("SUCESSO", msg);

            authenticationBean.setUser(user);
            System.out.println("Sucesso pelo o login novo " + username);
            return "success";
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login error", "Username e/ou Password inválidos.");

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("ERROR", msg);

            System.out.println("Login Failed - Login Novo");
            return "failure";
        }

    }

}
