package sessionBeans;

import entities.Actor;
import entities.Project;
import entities.UseCase;
import java.util.List;
import sessionBeans.AbstractService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 * @param <T>
 */
public interface ActorService extends AbstractService<Actor> {

    public List<Actor> findActorByProject(Project project);

    public List<Actor> findActorByUseCase(UseCase useCase);
}
