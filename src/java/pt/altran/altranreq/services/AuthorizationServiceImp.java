package pt.altran.altranreq.services;

import java.util.Collection;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
public class AuthorizationServiceImp implements AuthorizationService {

    private EntityManager em;
    private AltranreqRole role;

    public AuthorizationServiceImp() {
        this.em = null;
        this.role = null;
    }

    @WebMethod
    @Override
    public List<Project> getProjects(int userID) {

        List<ProjectUser> pu = em.
                createNamedQuery("ProjectUser.findByIdUser").
                setParameter("idUser", userID).
                getResultList();
        List<Project> projectList = null;
        for (ProjectUser p : pu) {
            projectList.add(p.getProject());
        }
        return projectList;
    }

    @WebMethod
    @Override
    public AltranreqRole getProjectRole(int projectID, int userID) {
        int roleId = (int) em.
                createNamedQuery("ProjectUser.findByIdUser").
                setParameter("idUser", userID).
                setParameter("idProject", projectID).
                getSingleResult();
        AltranreqRole role = (AltranreqRole) em.
                createNamedQuery("AltranreqRole.findByIdRole").
                setParameter("idRole", roleId).
                getSingleResult();
        if (role != null) {
            this.role = role;
        }
        return role;
    }

    @WebMethod
    @Override
    public Collection<Privilege> getRolePrivileges() {
        Collection<Privilege> p = this.role.getPrivilegeCollection();
        return p;
    }

    @WebMethod
    @Override
    public boolean hasPrivilege(int projectID, String privilege) {
        Collection<Privilege> col = this.role.getPrivilegeCollection();
        if (col.contains(privilege)) {
            return true;
        }
        return false;
    }

    @Override
    public void logout() {
        this.em = null;
        this.role = null;
    }

}
