package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.FunctionalRequirement;
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
import pt.altran.altranreq.services.AbstractServiceImp;
import pt.altran.altranreq.services.UseCaseService;

@WebService
@Stateless
public class UseCaseImp extends AbstractServiceImp<UseCase> implements UseCaseService {

        @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;

    
    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(UseCase.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @WebMethod
    public List<UseCase> findProjectsByDependency(UseCase usecase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<UseCase> findUseCaseByRequirement(FunctionalRequirement functionalRequirement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<UseCase> findUseCaseByDependency(UseCase useCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
