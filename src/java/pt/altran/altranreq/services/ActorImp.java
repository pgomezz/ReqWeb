package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.UseCase;
import java.util.List;
import javax.annotation.PostConstruct;
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
import pt.altran.altranreq.entities.FunctionalRequirement;

@Named
@Stateless
@WebService
public class ActorImp extends AbstractServiceImp<Actor> implements ActorService {
    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;

    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(Actor.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @WebMethod
    @Override
     public List<Actor> findActorByProject(Project project) {
         
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
        Root<Actor> ActorQuery = query.from(Actor.class);

        query.select(ActorQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (project.getName() != null && !project.getName().isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(ActorQuery.<String>get("name")), "%" + project.getName().toUpperCase() + "%");
            predicateList.add(namePredicate);
        }
        
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList();
         

     }
    
    @WebMethod
    @Override
     public List<Actor> findActorByUseCase(UseCase useCase) {
     
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Actor> query = cb.createQuery(Actor.class);
        Root<Actor> FuncReqQuery = query.from(Actor.class);

        query.select(FuncReqQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (useCase.getName() != null && !useCase.getName().isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(FuncReqQuery.<String>get("name")), "%" + useCase.getName().toUpperCase() + "%");
            predicateList.add(namePredicate);
        }
        
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList();
     }


}
