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
    @WebMethod
    public void init() {
        setEntityClass(FunctionalRequirement.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public List<FunctionalRequirement> findFunctionalRequirementByFilter(FunctionalRequirementFilter filter) {
       
    
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FunctionalRequirement> query = cb.createQuery(FunctionalRequirement.class);
        CriteriaQuery<FunctionalRequirement> c = cb.createQuery(FunctionalRequirement.class);
        ParameterExpression<String> name = cb.parameter(String.class, "name");
        ParameterExpression<Project> project = cb.parameter(Project.class, "project");
        ParameterExpression<RequirementState> state = cb.parameter(RequirementState.class, "state");
        ParameterExpression<BusinessCategory> BusinessCategory = cb.parameter(BusinessCategory.class, "catefory");
        Root<FunctionalRequirement> FuncReqQuery = query.from(FunctionalRequirement.class);

        query.select(FuncReqQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (filter.name != null && !filter.name.isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(FuncReqQuery.<String>get("name")), "%" + filter.name.toUpperCase() + "%");
            predicateList.add(namePredicate);
        }
        if (filter.projecto != null) {
            Expression<BigDecimal> idProject = FuncReqQuery.get("idFunctionalRequirement");
            Expression<BigDecimal> idProjectParam = cb.parameter(BigDecimal.class);
            Predicate projectPredicate = cb.equal(idProject, idProjectParam);
            predicateList.add(projectPredicate);
        }
        if (filter.state != null) {
            Expression<BigInteger> idState = FuncReqQuery.get("requirementState");
            Expression<BigInteger> idStateParam = cb.parameter(BigInteger.class);
            Predicate statePredicate = cb.equal(idState, idStateParam);
            predicateList.add(statePredicate);
        }
        if (filter.businessCategory != null) {
            
            Expression<BigInteger> idBusinessCategory = FuncReqQuery.get("idBusinessCategory");
            Expression<BigInteger> idBusinessCategoryParam = cb.parameter(BigInteger.class);
            Predicate typePredicate = cb.equal(idBusinessCategory, idBusinessCategoryParam);
            predicateList.add(typePredicate);
            
            
        }
        
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList();
    }
       
}
