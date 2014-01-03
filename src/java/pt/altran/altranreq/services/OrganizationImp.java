package pt.altran.altranreq.services;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.entities.UseCase;

@Stateless
public class OrganizationImp extends AbstractServiceImp<Organization> implements OrganizationService {

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

    @Override
    @WebMethod
    public Organization getOrganizationById(String id) {
        Query queryOrg = em.createNamedQuery("Organization.findByIdOrganization");
        BigDecimal idBD = new BigDecimal(id);
        queryOrg.setParameter("idOrganization", idBD);
        Organization organization = (Organization)queryOrg.getSingleResult();
        return organization;
    }
}
