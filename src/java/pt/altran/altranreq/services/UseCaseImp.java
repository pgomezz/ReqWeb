package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.util.Collection;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@WebService
@Stateless
public class UseCaseImp extends AbstractServiceImp<UseCase> implements UseCaseService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(UseCase.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<UseCase> findUseCaseByRequirement(int pai) {
        
        FunctionalRequirement f = new FunctionalRequirement();
        
        //f = getEntityManager().find(FunctionalRequirement.class, BigDecimal.valueOf(225));

        Query queryUC = em.createNamedQuery("UseCase.findUseCaseByIdReq");
        Query queryFR = em.createNamedQuery("FunctionalRequirement.findByIdFunctionalRequirement");
        queryFR.setParameter("idFunctionalRequirement",pai);
        FunctionalRequirement FR = (FunctionalRequirement) queryFR.getSingleResult();
        queryUC.setParameter("id", FR);
        Collection useCaseCollection = queryUC.getResultList();
        
        return (List<UseCase>) useCaseCollection;
    }

    @Override
    @WebMethod
    public List<UseCase> findUseCaseByDependency(int pai) {
        UseCase us = getEntityManager().find(UseCase.class, BigDecimal.valueOf(pai));

        return (List<UseCase>) us.getUseCaseCollection1();
    }
}