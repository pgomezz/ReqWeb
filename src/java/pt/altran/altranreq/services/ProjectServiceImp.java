package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigInteger;
import java.util.ArrayList;
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
import pt.altran.altranreq.entities.ProjectUser;
import pt.altran.altranreq.manager.builder.ProjectState;

@WebService
@Stateless
public class ProjectServiceImp extends AbstractServiceImp<Project> implements ProjectService {

    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod 
    public void init() {
     setEntityClass(Project.class);
    }
    
    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<Project> findProjectByFilter(ProjectFilter filter) {
        
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        CriteriaQuery<Project> c = cb.createQuery(Project.class);
        ParameterExpression<String> name = cb.parameter(String.class, "name");
        ParameterExpression<ProjectState> state = cb.parameter(ProjectState.class, "state");
        ParameterExpression<ProjectUser> user = cb.parameter(ProjectUser.class, "user");
         Root<Project> ProjectQuery = query.from(Project.class);

        query.select(ProjectQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (filter.name != null && !filter.name.isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(ProjectQuery.<String>get("name")), "%" + filter.name.toUpperCase() + "%");
            predicateList.add(namePredicate);
        }
        
        if (filter.state != null) {
            Expression<BigInteger> idState = ProjectQuery.get("projectState");
            Expression<BigInteger> idStateParam = cb.parameter(BigInteger.class);
            Predicate statePredicate = cb.equal(idState, idStateParam);
            predicateList.add(statePredicate);
        }
        if (filter.user != null) {
            
            Expression<BigInteger> idUser = ProjectQuery.get("projectUserCollection");
            Expression<BigInteger> idUserParam = cb.parameter(BigInteger.class);
            Predicate userPredicate = cb.equal(idUser, idUserParam);
            predicateList.add(userPredicate);
            
            
        }
        
        
        
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList(); }
    
    
}
