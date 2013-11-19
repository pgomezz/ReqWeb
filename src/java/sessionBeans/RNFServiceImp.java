package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Project;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;


public class RNFServiceImp extends AbstractServiceImp<Project> implements RNFService {

    @PostConstruct 
    public void init() {
     setEntityClass(Project.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Project> findRNFByFilter(RNFunctionalFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
