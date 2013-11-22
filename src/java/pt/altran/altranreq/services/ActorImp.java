package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import pt.altran.altranreq.entities.FunctionalRequirement;

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
    @Override
     public List<Actor> findActorByProject(int idProject) {
         
  
        Project project = getEntityManager().find(Project.class, BigDecimal.valueOf(idProject));
        Collection<FunctionalRequirement> listarefunc = project.getFunctionalRequirementCollection();
        ArrayList<UseCase> listaCasosUso = new ArrayList<>();
         for (FunctionalRequirement functionalRequirement : listarefunc) {
             for (UseCase useCase : functionalRequirement.getUseCaseCollection()) {
                 listaCasosUso.add(useCase);
             }
        } 
        ArrayList<Actor> listaActores = new ArrayList<>();
        
        for (UseCase us : listaCasosUso) {
            for (Actor actor : us.getActorCollection()) {
                listaActores.add(actor);
            }
        }
         

        return listaActores;
     }
    
    @WebMethod
    @Override
     public List<Actor> findActorByUseCase(int idUseCase) {
     
       UseCase useCase = getEntityManager().find(UseCase.class, BigDecimal.valueOf(idUseCase));

        return  (List<Actor>)useCase.getActorCollection();
     }


}
