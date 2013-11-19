package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Project;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Named(value = "userServiceImp")
@Stateless
public class UserServiceImp extends AbstractServiceImp<AltranreqUser> implements UserService {

    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(AltranreqUser.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<AltranreqUser> findUsersByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<AltranreqUser> findUsersByStatus(Boolean bool) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<AltranreqUser> findUsersByUserName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<AltranreqUser> isAdmin(Boolean bool) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public boolean isUserValid(AltranreqUser altranrequser, String hash) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public boolean hasPermition(AltranreqUser altranrequser, Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
