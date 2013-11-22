package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import pt.altran.altranreq.entities.AlternativeFlows;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.entities.UseCase;

@Stateless
@WebService
public class AlternativeFlowsImp extends AbstractServiceImp<AlternativeFlows> implements AlternativeFlowsService {

            @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod 
    public void init() {
     setEntityClass(AlternativeFlows.class);
    }
    
    @Override
    @WebMethod
    public List<AlternativeFlows> findAlternativeFlowsByUseCase(int idUseCase) {
        
        UseCase useCase = getEntityManager().find(UseCase.class, BigDecimal.valueOf(idUseCase));

        return  (List<AlternativeFlows>)useCase.getAlternativeFlowsCollection();
        
        
        
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}
