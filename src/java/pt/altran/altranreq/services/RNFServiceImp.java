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
import java.util.ResourceBundle;
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
import pt.altran.altranreq.manager.util.TypeNonFunctionalEnum;

@WebService
@Stateless
public class RNFServiceImp extends AbstractServiceImp<NonFunctionalRequirement> implements RNFService {

    @PersistenceContext(unitName = "AltranReqPU")
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
        Root<NonFunctionalRequirement> nonFuncReqQuery = query.from(NonFunctionalRequirement.class);

        query.select(nonFuncReqQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (filter.getName() != null && !filter.getName().isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(nonFuncReqQuery.<String>get("name")), "%" + filter.getName().toUpperCase() + "%");
            predicateList.add(namePredicate);
        }
        if (filter.getProject() != null) {
            Project p = new Project();
            p.setIdProject(BigDecimal.valueOf(filter.getProject()));

            Predicate projectPredicate = cb.equal(nonFuncReqQuery.<Project>get("idProject"), p);
            predicateList.add(projectPredicate);
        }
        if (filter.getState() != null) {

            Predicate statePredicate = cb.equal(nonFuncReqQuery.<BigInteger>get("requirementState"), filter.getState());
            predicateList.add(statePredicate);
        }
        if (filter.getType() != null) {

            Predicate typePredicate = cb.equal(nonFuncReqQuery.<BigInteger>get("type"), filter.getType());
            predicateList.add(typePredicate);

        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);

        return getEntityManager().createQuery(query).getResultList();
    }

    @Override
    @WebMethod
    public List<NonFunctionalRequirement> findRNFByDependency(int pai) {
        NonFunctionalRequirement us = getEntityManager().find(NonFunctionalRequirement.class, BigDecimal.valueOf(pai));

        return (List<NonFunctionalRequirement>) us.getNonFunctionalRequirementCollection();

    }

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

    @Override
    public String getRequirementTypeString(int requirementTypeIndice) {
        return ResourceBundle.getBundle("/bundle_nfrType").getString(TypeNonFunctionalEnum.getByIndex(requirementTypeIndice).toString());
    }

    @Override
    public boolean existNRFByName(String name, int idProj) {
        RNFunctionalFilter frName = new RNFunctionalFilter();
        frName.setName(name);
        frName.setProject(idProj);
        
        for (NonFunctionalRequirement fr : findRNFByFilter(frName)){
            if (fr.getName().equalsIgnoreCase(name))
                return true;
        }
        
        return false;
    }

}
