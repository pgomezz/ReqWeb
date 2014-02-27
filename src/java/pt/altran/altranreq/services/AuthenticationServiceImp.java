package pt.altran.altranreq.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.manager.AuthenticationController;

@WebService
@Stateless
public class AuthenticationServiceImp implements AuthenticationService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @Inject
    private PasswordEncryptionService passwordService;

    public AuthenticationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public AltranreqUser login(String username, String password) {
        System.out.println("Cenas " + username);
       AltranreqUser u = null;
       byte[] newPassword = null;
        try {
            newPassword = passwordService.getEncryptedPassword(password, "AltranREQ".getBytes());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(AuthenticationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            System.out.println("Cenas 1" + username);
                 u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsernameAndPassword").
                setParameter("username", username).
                setParameter("password", new String(newPassword)). //new String(newPassword)
                getSingleResult();
                System.out.println("Cenas 2" + username);
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return u;
    }



}
