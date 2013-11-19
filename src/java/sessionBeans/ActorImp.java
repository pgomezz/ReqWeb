package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Actor;
import entities.Project;
import entities.UseCase;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

public class ActorImp extends AbstractServiceImp<Actor> implements ActorService {

    @PostConstruct
    public void init() {
        setEntityClass(Actor.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
     public List<Actor> findActorByProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
     public List<Actor> findActorByUseCase(UseCase useCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
