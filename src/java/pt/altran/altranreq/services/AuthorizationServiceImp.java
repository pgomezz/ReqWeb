package pt.altran.altranreq.services;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
public class AuthorizationServiceImp implements AuthorizationService {

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    public AuthorizationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public List<ProjectUser> getProjects(int userID) { //ERRO PARA RESOLVER
        List<ProjectUser> pu_list = em.
                createNamedQuery("ProjectUser.findByIdUser").
                setParameter("idUser", userID).
                getResultList();
        /*List<Project> allProject = em.createNamedQuery("Project.findAll").getResultList();
        List<Project> projectList = null;
        Project p = null;
        for (int j = 0; j < pu_list.size(); j++) {
            for (int i = 0; i < allProject.size(); i++) {
                if (pu_list.get(j).getProject().getIdProject().intValue()
                        == allProject.get(i).getIdProject().intValue()) {
                    p = allProject.get(i);
                    projectList.add(p);
                }
            }
        }*/
        return pu_list;
    }

    @WebMethod
    @Override
    public AltranreqRole getProjectRole(int projectID, int userID) {
        //Query para o ProjectUser -> findRoleIdByIdProjIdUser
        //SELECT ID_ROLE FROM ( SELECT p FROM ProjectUser p WHERE p.projectUserPK.idProject = :idProject AND p.projectUserPK.idUser = :idUser)
        ProjectUser p_user = (ProjectUser) em.
                createNamedQuery("ProjectUser.findByIdProjIdUser").
                setParameter("idUser", userID).
                setParameter("idProject", projectID).
                getSingleResult();
        int idRole = p_user.getIdRole().getIdRole().intValueExact();
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
