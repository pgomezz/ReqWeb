package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.rmi.UnexpectedException;
import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.UseCase;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
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
    }

    @WebMethod
     public List<Actor> findActorByProject(Project project) {
         
        //CriteriaQuery c = getEntityManager().getCriteriaBuilder().createQuery();
        //c.select(c.from(Project.class));
        throw  new UnsupportedOperationException("");
     }
    
    @WebMethod
     public List<Actor> findActorByUseCase(UseCase useCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
