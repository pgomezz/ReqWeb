package pt.altran.altranreq.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.emptyList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUser;

@WebService
@Named(value = "authorizationService")
@SessionScoped
public class AuthorizationServiceImp implements AuthorizationService, Serializable{

    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @Context
    private int userID;
    @Context
    private int projectID;
    @Context
    private boolean isAdmin;
    
    public AuthorizationServiceImp() {
        this.em = null;
    }

    @WebMethod
    @Override
    public List<Project> getProjectsFromUser() {
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
    public AltranreqRole getProjectRole() {
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

    @Override
    public List<AltranreqUser> getUsersByProject() {
        List<ProjectUser> p_user = em.
                createNamedQuery("ProjectUser.findByIdProjIdRole").
                setParameter("idProject", projectID).
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
    public boolean hasPrivilege(String privilege) {
//        AltranreqRole role = this.getProjectRole(userID);
//        if (role.getPrivilegeCollection().contains(privilege)) {
//            return true;
//        }
        return false;
    }

    @Override
    public void setProject(int projectID) {
        this.projectID = projectID;
    }
    
    @Override
    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }

    @WebMethod
    @Override
    public boolean isAdmin() {
        return isAdmin;
    }
    
    @WebMethod
    @Override
    public void setUserID(int userID){
        this.userID = userID;
    }
    
}