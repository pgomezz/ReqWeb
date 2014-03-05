package pt.altran.altranreq.services;

import java.util.Collection;
import java.util.List;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;

public interface AuthorizationService {

    public List<Project> getProjectsFromUser();

    public AltranreqRole getProjectRole();

    public Collection<Privilege> getRolePrivileges(AltranreqRole role);

    public List<AltranreqUser> getUsersByProject();

    public boolean hasPrivilege(String privilege);

    public void setProject(int project);

    public boolean isAdmin();

    public void setUserID(int userID);
    
    public void setIsAdmin(boolean isAdmin);
}
