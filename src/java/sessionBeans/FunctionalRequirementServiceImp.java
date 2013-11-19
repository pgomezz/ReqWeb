package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.AltranreqRole;
import entities.FunctionalRequirement;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class FunctionalRequirementServiceImp extends AbstractServiceImp <FunctionalRequirement> implements FunctionalRequirementService{
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        setEntityClass(FunctionalRequirement.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
