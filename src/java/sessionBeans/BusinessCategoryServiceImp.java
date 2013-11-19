package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.BusinessCategory;
import entities.FunctionalRequirement;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class BusinessCategoryServiceImp extends AbstractServiceImp <BusinessCategory> implements BusinessCategoryService {
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    public List <FunctionalRequirement> findProjectsByFilter(FunctionalRequirementFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
