package pt.altran.altranreq.services;

import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqUser;

@WebService
@Stateless
public class AuthenticationServiceImp implements AuthenticationService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public AuthenticationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public AltranreqUser login(String username, String password) {
       AltranreqUser u = null;
        try{
                 u = (AltranreqUser) em.
                createNamedQuery("AltranreqUser.findByUsernameAndPassword").
                setParameter("username", username).
                setParameter("password", password).
                getSingleResult();
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        return u;
    }



}
