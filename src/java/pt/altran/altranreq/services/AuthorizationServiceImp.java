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

    public AuthorizationServiceImp() {
        this.em = null;
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
        //Query para o ProjectUser -> findRoleIdByIdProjIdUser
        //SELECT ID_ROLE FROM ( SELECT p FROM ProjectUser p WHERE p.projectUserPK.idProject = :idProject AND p.projectUserPK.idUser = :idUser)
        int idRole = (int) em.
                createNamedQuery("ProjectUser.findRoleIdByIdProjIdUser").
                setParameter("idUser", userID).
                setParameter("idProject", projectID).
                getSingleResult();
        AltranreqRole role = (AltranreqRole) em.
                createNamedQuery("AltranreqRole.findByIdRole").
                setParameter("idRole", idRole).
                getSingleResult();
        return role;
    }

    @WebMethod
    @Override
    public Collection<Privilege> getRolePrivileges(AltranreqRole role) {
        Collection<Privilege> p = role.getPrivilegeCollection();
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
