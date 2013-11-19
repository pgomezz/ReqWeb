package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.FunctionalRequirement;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@WebService
@Stateless
public class FunctionalRequirementServiceImp extends AbstractServiceImp <FunctionalRequirement> implements FunctionalRequirementService{
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(FunctionalRequirement.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
