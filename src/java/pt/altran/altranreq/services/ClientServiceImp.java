package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.Client;
import pt.altran.altranreq.entities.FunctionalRequirement;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Stateless
//@Stateful
public class ClientServiceImp extends AbstractServiceImp <Client> implements ClientService{
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @WebMethod
    @PostConstruct
    public void init() {
        setEntityClass(Client.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @WebMethod
    public List <FunctionalRequirement> findProjectsByFilter(FunctionalRequirementFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
