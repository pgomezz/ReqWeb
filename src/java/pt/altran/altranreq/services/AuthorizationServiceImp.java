package pt.altran.altranreq.services;

import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.emptyList;
import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
@Stateless
public class AuthorizationServiceImp implements AuthorizationService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public AuthorizationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public List<Project> getProjects(int userID) {
        List<ProjectUser> pu_list = em.
                createNamedQuery("ProjectUser.findByIdUser").
                setParameter("idUser", userID).
                getResultList();
        List<Project> allProject = em.createNamedQuery("Project.findAll").getResultList();
        List<Project> projectList = new ArrayList<>();
        Project p = null;
        for (int j = 0; j < pu_list.size(); j++) {
            for (int i = 0; i < allProject.size(); i++) {
                if (pu_list.get(j).getProject().getIdProject().intValue()
                        == allProject.get(i).getIdProject().intValue()) {
                    p = allProject.get(i);
                    projectList.add(p);
                }
            }
        }
        if (projectList.isEmpty()) {
            return emptyList();
        }
        return projectList;

    }

    @WebMethod
    @Override
    public AltranreqRole getProjectRole(int projectID, int userID) {
        ProjectUser p_user = (ProjectUser) em.
                createNamedQuery("ProjectUser.findByIdProjIdUser").
                setParameter("idUser", userID).
                setParameter("idProject", projectID).
                getSingleResult();
        int idRole = p_user.getIdRole().getIdRole().intValueExact();
        AltranreqRole role = null;
        role = (AltranreqRole) em.
                createNamedQuery("AltranreqRole.findByIdRole").
                setParameter("idRole", idRole).
                getSingleResult();
        return role;
    }

    @Override
    public List<AltranreqUser> getUsersByProject(int projectID, int idRole) {
        List<ProjectUser> p_user = em.
                createNamedQuery("ProjectUser.findByIdProjIdRole").
                setParameter("idProject", projectID).
                setParameter("idRole", idRole).
                getResultList();
        List<AltranreqUser> user_list = new ArrayList<>();
        List<AltranreqUser> all_users = em.
                createNamedQuery("AltranreqUser.findAll").getResultList();
        for (ProjectUser p : p_user) {
            for (int i = 0; i < all_users.size(); i++) {
                if (p.getProjectUserPK().getIdProject().intValue()
                        == all_users.get(i).getIdUser().intValue()) {
                    user_list.add(all_users.get(i));
                }
            }
        }
        if (user_list.isEmpty()) {
            return emptyList();
        } else {
            return user_list;
        }
    }

    @WebMethod
    @Override
    public Collection<Privilege> getRolePrivileges(AltranreqRole role) {
        Collection<Privilege> p = null;
        if (!role.getPrivilegeCollection().isEmpty()) {
            return role.getPrivilegeCollection();
        }
        if (p.isEmpty()) {
            return emptyList();
        }
        return p;
    }

    @WebMethod
    @Override
    public boolean hasPrivilege(int projectID, int userID, String privilege) {
        AltranreqRole role = this.getProjectRole(projectID, userID);
        if (role.getPrivilegeCollection().contains(privilege)) {
            return true;
        }
        return false;
    }

}
