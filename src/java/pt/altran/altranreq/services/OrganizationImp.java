package pt.altran.altranreq.services;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.Organization;

@Stateless
public class OrganizationImp extends AbstractServiceImp<Organization> implements OrganizationService{

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(Organization.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }
}
