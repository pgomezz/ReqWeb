package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import java.util.List;
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
import pt.altran.altranreq.entities.RNFType;
import pt.altran.altranreq.entities.RequirementState;

@WebService
@Stateless
public class RNFServiceImp extends AbstractServiceImp<NonFunctionalRequirement> implements RNFService {

    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod 
    public void init() {
     setEntityClass(NonFunctionalRequirement.class);
    }
    
    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<NonFunctionalRequirement> findRNFByFilter(RNFunctionalFilter filter) {
    
         CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<NonFunctionalRequirement> query = cb.createQuery(NonFunctionalRequirement.class);
        CriteriaQuery<NonFunctionalRequirement> c = cb.createQuery(NonFunctionalRequirement.class);
            ParameterExpression<String> nome = cb.parameter(String.class, "nome");
            ParameterExpression<Project> project = cb.parameter(Project.class, "project");
            ParameterExpression<RequirementState> state = cb.parameter(RequirementState.class, "state");
            ParameterExpression<RNFType> type = cb.parameter(RNFType.class, "type");
            Root<NonFunctionalRequirement> nonFuncReqQuery = query.from(NonFunctionalRequirement.class);
            
            query.select(nonFuncReqQuery);            
            
            List<Predicate> predicateList = new ArrayList<>();
            
        if (filter.name !=null && !filter.name.isEmpty()) {//TODO falar com o Pessegueiro
             Predicate namePredicate = cb.like(
                     cb.upper(nonFuncReqQuery.<String>get("name")), "%"+filter.name.toUpperCase()+"%");
            predicateList.add(namePredicate);
        }
        if (filter.project !=null) {            
            Expression<BigDecimal> idProject = nonFuncReqQuery.get("IdNonFuncRequirement");
            Expression<BigDecimal> idProjectParam = cb.parameter(BigDecimal.class);
            Predicate projectPredicate = cb.equal(idProject,idProjectParam);
            predicateList.add(projectPredicate);
        }
        if (filter.state!=null) {
            Expression<BigInteger> idState = nonFuncReqQuery.get("requirementState");
            Expression<BigInteger> idStateParam = cb.parameter(BigInteger.class);
            Predicate statePredicate = cb.equal(idState,idStateParam);
            predicateList.add(statePredicate);
        }
        if (filter.type !=null) {
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        
        return getEntityManager().createQuery(query).getResultList();
    }


   
    
    
}
