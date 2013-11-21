package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.math.BigDecimal;
import java.util.ArrayList;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;
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
import pt.altran.altranreq.entities.AlternativeFlows;
import pt.altran.altranreq.services.AbstractServiceImp;
import pt.altran.altranreq.services.UseCaseService;

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


      FunctionalRequirement f = getEntityManager().find(FunctionalRequirement.class, BigDecimal.valueOf(pai));
       return (List<UseCase>)f.getUseCaseCollection();

    }

    @Override
    @WebMethod
    public List<UseCase> findUseCaseByDependency(int pai) {

        
        UseCase us = getEntityManager().find(UseCase.class, BigDecimal.valueOf(pai));
        
        
        return (List<UseCase>)us.getUseCaseCollection1();

        
//        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<UseCase> query = cb.createQuery(UseCase.class);
//        Root<UseCase> aFQuery = query.from(UseCase.class);
//
//        query.select(aFQuery);
//
//        List<Predicate> predicateList = new ArrayList<>();
//
//        if (pai.getIdUseCase() != null) {
//            Expression<BigDecimal> idUseCase = aFQuery.get("idUseCase");
//            Expression<BigDecimal> idUseCaseParam = cb.parameter(BigDecimal.class);
//            Predicate useCasePredicate = cb.equal(idUseCase, idUseCaseParam);
//            predicateList.add(useCasePredicate);
//        }
//
//        Predicate[] predicates = new Predicate[predicateList.size()];
//        predicateList.toArray(predicates);
//        query.where(predicates);
//        List<UseCase> listaIDs = getEntityManager().createQuery(query).getResultList();
//
//        CriteriaBuilder cbUseCase = getEntityManager().getCriteriaBuilder();
//        CriteriaQuery<UseCase> queryUseCase = cbUseCase.createQuery(UseCase.class);
//        Root<UseCase> aFQueryUseCase = queryUseCase.from(UseCase.class);
//
//        queryUseCase.select(aFQueryUseCase);
//
//       
//        predicateList = new ArrayList<>();
//        for (UseCase bigDecimal : listaIDs) {
//            Expression<UseCase> idUseCase = aFQueryUseCase.get("idUseCase");
//            Expression<UseCase> idUseCaseParam = cbUseCase.parameter(UseCase.class);
//            Predicate useCasePredicate = cbUseCase.equal(idUseCase, idUseCaseParam);
//            predicateList.add(useCasePredicate);            
//        }
//
//       predicates = new Predicate[predicateList.size()];
//        predicateList.toArray(predicates);
//        queryUseCase.where(predicates);
//        return getEntityManager().createQuery(queryUseCase).getResultList();
    }

}
