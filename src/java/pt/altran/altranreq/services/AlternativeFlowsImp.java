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
    public List<AlternativeFlows> findAlternativeFlowsByUseCase(AlternativeFlows alternativeFlows) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<AlternativeFlows> query = cb.createQuery(AlternativeFlows.class);
        Root<AlternativeFlows> AFQuery = query.from(AlternativeFlows.class);

        query.select(AFQuery);

        List<Predicate> predicateList = new ArrayList<>();

        
        if (alternativeFlows.getIdUseCase() != null) {

            Expression<BigDecimal> idUseCase = AFQuery.get("idUseCase");
            Expression<BigDecimal> idUseCaseParam = cb.parameter(BigDecimal.class);
            Predicate useCasePredicate = cb.equal(idUseCase, idUseCaseParam);
            predicateList.add(useCasePredicate);
        }
        
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList();
        
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}
