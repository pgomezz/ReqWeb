package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import pt.altran.altranreq.entities.FunctionalRequirement;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pt.altran.altranreq.entities.BusinessCategory;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.RequirementState;

/**
 *
 * @author User
 */
@WebService
@Stateless
public class FunctionalRequirementServiceImp extends AbstractServiceImp <FunctionalRequirement> implements FunctionalRequirementService{
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        setEntityClass(FunctionalRequirement.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    @WebMethod
    @Override
    public List<FunctionalRequirement> findFunctionalRequirementByFilter(FunctionalRequirementFilter filter) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FunctionalRequirement> query = cb.createQuery(FunctionalRequirement.class);
        Root<FunctionalRequirement> funcReqQuery = query.from(FunctionalRequirement.class);

        query.select(funcReqQuery);

        List<Predicate> predicateList = new ArrayList<>();
    
    
        
        
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(funcReqQuery.<String>get("name")), "%" + filter.getName().toUpperCase() + "%");
            predicateList.add(namePredicate);
            
                    

        }
        if (filter.getProjecto() != null) {

            
            // FunctionalRequirement us = getEntityManager().find(FunctionalRequirement.class, BigDecimal.valueOf(filter.getProjecto()));
        
            
            
            Project p = new Project();
            p.setIdProject(BigDecimal.valueOf(filter.getProjecto()));
            
            Predicate namePredicate = cb.equal(funcReqQuery.<Project>get("idProject"), p);
            predicateList.add(namePredicate);
            
            
//            Expression<BigDecimal> idProject = funcReqQuery.get("idFunctionalRequirement");
//            Expression<BigDecimal> idProjectParam = cb.parameter(BigDecimal.class);
//            Predicate projectPredicate = cb.equal(idProject, idProjectParam);
//            predicateList.add(projectPredicate);
            
        }
        if (filter.getState() != null) {


            Predicate namePredicate = cb.equal(funcReqQuery.<BigInteger>get("requirementState"), filter.getState());
            predicateList.add(namePredicate);
            
        }
        if (filter.getBusinessCategory() != null) {
            
            Expression<BigInteger> idBusinessCategory = funcReqQuery.get("idBusinessCategory");
            Expression<BigInteger> idBusinessCategoryParam = cb.parameter(BigInteger.class);
            Predicate typePredicate = cb.equal(idBusinessCategory, idBusinessCategoryParam);
            predicateList.add(typePredicate);
            
            
        }
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        List<FunctionalRequirement> z = getEntityManager().createQuery(query).getResultList();
        
        return z;
    }
    
    
    }
    

