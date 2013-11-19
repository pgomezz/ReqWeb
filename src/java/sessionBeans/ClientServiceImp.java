package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.AltranreqRole;
import entities.Client;
import entities.FunctionalRequirement;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class ClientServiceImp extends AbstractServiceImp <Client> implements ClientService{
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        setEntityClass(Client.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public List <FunctionalRequirement> findProjectsByFilter(FunctionalRequirementFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
