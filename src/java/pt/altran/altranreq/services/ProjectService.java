package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import pt.altran.altranreq.entities.Project;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 */
public interface ProjectService extends AbstractService<Project> {
    
    public List<Project> findProjectByFilter(ProjectFilter filter);

    String getProjectStateString(int projectStateIndice);

    String getProjectUserName(int idUser);
}