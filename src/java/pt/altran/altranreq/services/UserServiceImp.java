package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import pt.altran.altranreq.entities.AltranreqUser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
@Named(value = "userServiceImp")
@Stateless
public class UserServiceImp extends AbstractServiceImp<AltranreqUser> implements UserService {

    @PersistenceContext(unitName = "AltranReqPU")
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
        List<AltranreqUser> userList = em.
                createNamedQuery("AltranreqUser.findByName").
                setParameter("name", name).
                getResultList();
        return userList;
    }

    @Override
    @WebMethod
    public List<AltranreqUser> findUsersByStatus(Boolean bool) {
        List<AltranreqUser> userList = em.
                createNamedQuery("AltranreqUser.findAll").
                getResultList();
        List<ProjectUser> projecUserList = em.
                createNamedQuery("ProjectUser.findAll").
                getResultList();
        for (AltranreqUser a : userList) {
            if (!bool) {
                if (projecUserList.contains(a)) {
                    userList.remove(a);
                }
            } else {
                if (!projecUserList.contains(a)) {
                    userList.remove(a);
                }
            }
        }
        return userList;
    }

    @Override
    @WebMethod
    public List<AltranreqUser> findUsersByUserName(String username) {
        List<AltranreqUser> userList = em
                .createNamedQuery("AltranreqUser.findAll")
                .getResultList();
        for (AltranreqUser u : userList) {
            if (!u.getName().contains(username) && !u.getUsername().contains(username)) {
                userList.remove(u);
            }
        }
        return userList;
    }

    @Override
    @WebMethod
    public List<AltranreqUser> isAdmin(Boolean bool) {
        List<AltranreqUser> userlist = 
                em.createNamedQuery("AltranreqUser.findByIsAdmin")
                .getResultList();
        return userlist;
    }

}
