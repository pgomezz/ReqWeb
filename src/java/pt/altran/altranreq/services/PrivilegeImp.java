package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.Privilege;

@WebService
@Stateless
public class PrivilegeImp extends AbstractServiceImp<Privilege> implements PrivilegeService {

@PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;

    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(Privilege.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }
    
    

}
