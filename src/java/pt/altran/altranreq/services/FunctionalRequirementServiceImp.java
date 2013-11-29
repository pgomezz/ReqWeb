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
import java.util.ResourceBundle;
import pt.altran.altranreq.entities.FunctionalRequirement;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pt.altran.altranreq.entities.BusinessCategory;
import pt.altran.altranreq.entities.Project;

/**
 *
 * @author User
 */
@WebService
@Stateless
public class FunctionalRequirementServiceImp extends AbstractServiceImp<FunctionalRequirement> implements FunctionalRequirementService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        setEntityClass(FunctionalRequirement.class);
    }

    protected EntityManager getEntityManager() {
        return em;
    }

    //this method is used to filter searches by the parameters specified in the FunctionalRequirementFilter class.
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

            Project p = new Project();
            p.setIdProject(BigDecimal.valueOf(filter.getProjecto()));

            Predicate namePredicate = cb.equal(funcReqQuery.<Project>get("idProject"), p);
            predicateList.add(namePredicate);


        }
        if (filter.getState() != null) {

            Predicate namePredicate = cb.equal(funcReqQuery.<BigInteger>get("requirementState"), filter.getState());
            predicateList.add(namePredicate);

        }
        if (filter.getBusinessCategory() != null) {


            BusinessCategory bc = new BusinessCategory();
            bc.setIdBusinessCategory(BigDecimal.valueOf(filter.getBusinessCategory()));

            Predicate namePredicate = cb.equal(funcReqQuery.<BusinessCategory>get("idBusinessCategory"), bc);
            predicateList.add(namePredicate);

        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        List<FunctionalRequirement> z = getEntityManager().createQuery(query).getResultList();

        return z;
    }

    //this method converts the id of the requirement state to it´s text meaning so it can be more perceptive to the user
    @Override
    public String getRequirementStateString(int requirementStateIndice) {
    switch (requirementStateIndice) {
            case 0:
                return ResourceBundle.getBundle("/bundle_requirement").getString("State_active");
            case 1:
                return ResourceBundle.getBundle("/bundle_requirement").getString("State_versioned");
            case 2:
                return ResourceBundle.getBundle("/bundle_requirement").getString("State_cancelled");
            case 3:
                return ResourceBundle.getBundle("/bundle_requirement").getString("State_done");
            default:
                return ResourceBundle.getBundle("/bundle_requirement").getString("State_undefined");
        }    
    
    
    }

}
