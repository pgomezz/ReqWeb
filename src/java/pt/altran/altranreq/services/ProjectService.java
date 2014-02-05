package pt.altran.altranreq.services;

import pt.altran.altranreq.entities.Project;
import java.util.List;

public interface ProjectService extends AbstractService<Project> {
    
    public List<Project> findProjectByFilter(ProjectFilter filter);

    String getProjectStateString(int projectStateIndice);

    String getProjectUserName(int idUser);
    
    boolean existProjectByName(String name);
}