package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import pt.altran.altranreq.entities.Project;
import java.util.List;
import java.util.Properties;
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
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
@Stateless
public class ProjectServiceImp extends AbstractServiceImp<Project> implements ProjectService {

    static private Properties projectStateStrings = null;

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    //
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

    private List<Project> findProjectByUser(int pu) {

        Collection<ProjectUser> listapu = getEntityManager().find(Project.class, BigDecimal.valueOf(pu)).getProjectUserCollection();
        List<Project> projectsLists = new ArrayList<>();
        for (ProjectUser projectUser : listapu) {
            projectsLists.add(projectUser.getProject());
        }

        return projectsLists;
    }

    @Override
    public String getProjectUserName(int idUser) {
        try {
            AltranreqUser altranreqUser = getEntityManager().find(AltranreqUser.class, idUser);
            String nome = altranreqUser.getName();
            return nome;
        } catch (IllegalArgumentException ia) {
            return "projectuser invalid for this project";
        }
    }

    @Override
    public String getProjectStateString(int projectStateIndice) {
        switch (projectStateIndice) {
            case 0:
                return ResourceBundle.getBundle("/projectstates").getString("ongoing");
            case 1:
                return ResourceBundle.getBundle("/projectstates").getString("standby");
            case 2:
                return ResourceBundle.getBundle("/projectstates").getString("done");
            case 3:
                return ResourceBundle.getBundle("/projectstates").getString("mantainence");
            default:
                return ResourceBundle.getBundle("/projectstates").getString("undefined");
        }

    }

    @Override
    @WebMethod
    public List<Project> findProjectByFilter(ProjectFilter filter) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Project> query = cb.createQuery(Project.class);
        Root<Project> ProjectQuery = query.from(Project.class);

        query.select(ProjectQuery);

        List<Predicate> predicateList = new ArrayList<>();

        if (filter.getUser() != null && filter.getState() == null && filter.getName()
                == null) {

            return findProjectByUser(filter.getUser());
        }
        if (filter.getName() != null && !filter.getName().isEmpty()) {
            Predicate namePredicate = cb.like(
                    cb.upper(ProjectQuery.<String>get("name")), "%" + filter.getName().toUpperCase() + "%");
            predicateList.add(namePredicate);
        }

        if (filter.getState() != null) {

            Predicate namePredicate = cb.equal(ProjectQuery.<BigInteger>get("projectState"), filter.getState());
            predicateList.add(namePredicate);
        }

        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        query.where(predicates);
        List<Project> listaProj = getEntityManager().createQuery(query).getResultList();
        List<Project> listaReturn = new ArrayList<>();
        if (filter.getUser() != null) {
            for (Project project : listaProj) {
                for (ProjectUser projectUser : project.getProjectUserCollection()) {
                    if (!projectUser.getProjectUserPK().getIdUser().equals(BigDecimal.valueOf(filter.getUser()))) {
                        listaReturn.add(project);

                    }
                }
            }
            return listaReturn;
        }

        return listaProj;

    }

}
