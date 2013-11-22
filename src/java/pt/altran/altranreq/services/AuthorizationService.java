package pt.altran.altranreq.services;

import java.util.Collection;
import java.util.List;
import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Privilege;
import pt.altran.altranreq.entities.Project;


public interface AuthorizationService {

    public List<Project> getProjects(int userID);

    public AltranreqRole getProjectRole(int projectID, int userID);

    public Collection<Privilege> getRolePrivileges(AltranreqRole role);

    public boolean hasPrivilege(int projectID, int userID, String privilege);

    

}
