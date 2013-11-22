package pt.altran.altranreq.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqUser;

@WebService
public class AuthenticationServiceImp implements AuthenticationService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public AuthenticationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public AltranreqUser Login(String username, String password) {
        AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.UserPassword").
                setParameter("username", username).
                setParameter("password", password).
                getSingleResult();
        return u;
    }

    @WebMethod
    @Override
    public boolean isAdmin(AltranreqUser user) {
        if (user.getIsAdmin() == '1') {
            return true;
        }
        return false;
    }

}
