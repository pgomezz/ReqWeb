package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import pt.altran.altranreq.entities.AltranreqRole;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Stateless
public class AltranReqRoleImp extends AbstractServiceImp<AltranreqRole> implements AltranReqRoleService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        setEntityClass(AltranreqRole.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<AltranreqRole> findRoleByName(String name) {
        List<AltranreqRole> r = em.
                createNamedQuery("AltranreqRole.all").
                getResultList();
        for (AltranreqRole a : r){
            if(a.getDescription().contains(name)==false){
                r.remove(a);
            }
        }
        return r;
    }

    @Override
    @WebMethod
    public List<AltranreqRole> findRoleByState(Boolean bool) {
        List<AltranreqRole> r = em.
                createNamedQuery("AltranreqRole.findAll").
                getResultList();
        return r;
    }

}
