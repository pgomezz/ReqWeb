package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.UseCase;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@Named
@Stateless
@WebService
public class ActorImp extends AbstractServiceImp<Actor> implements ActorService {
    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;

    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(Actor.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @WebMethod
     public List<Actor> findActorByProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @WebMethod
     public List<Actor> findActorByUseCase(UseCase useCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
