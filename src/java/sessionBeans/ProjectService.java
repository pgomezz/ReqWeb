package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.Project;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 */
public interface ProjectService extends AbstractService<Project> {
    
    public List<Project> findProjectsByFilter(ProjectFilter filter);

}
