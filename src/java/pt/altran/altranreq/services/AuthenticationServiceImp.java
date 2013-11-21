package pt.altran.altranreq.services;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqUser;

@WebService
public class AuthenticationServiceImp implements AuthenticationService {

    private AltranreqUser user;
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public AuthenticationServiceImp() {
        this.user = null;
        this.em = null;
    }

    @WebMethod
    @Override
    public AltranreqUser Login(String username, String password) {
        AltranreqUser u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUserPass").
                setParameter("username", username).
                setParameter("password", password).
                getSingleResult();
        if (u != null) {
            this.user = u;
        }
        return u;
    }

    @WebMethod
    @Override
    public AltranreqUser getUser() {
        return this.user;
    }

    @Override
    public void logout() {
        em.clear();
        em.close();

    }

    @WebMethod
    @Override
    public boolean isAdmin(AltranreqUser user) {
        if (this.user.getIsAdmin() == '1') {
            return true;
        }
        return false;
    }

}
