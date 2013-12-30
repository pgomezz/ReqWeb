package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import pt.altran.altranreq.entities.AltranreqUser;
import java.util.List;
import pt.altran.altranreq.entities.Project;

/**
 *
 * @author User
 * @param <T>
 */
public interface UserService extends AbstractService<AltranreqUser> {
    public List<AltranreqUser> findUsersByName(String name);
    
    public List<AltranreqUser> findUsersByProject(Project project);
    
    public List<AltranreqUser> findUsersNotInProject(Project project);

    public List<AltranreqUser> findUsersByUserName(String username);

    public List<AltranreqUser> isAdmin(Boolean bool);
}