package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.Project;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@WebService
@Stateless
public class ProjectServiceImp extends AbstractServiceImp<Project> implements ProjectService {

    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod 
    public void init() {
     setEntityClass(Project.class);
    }
    
    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @WebMethod
    public List<Project> findProjectsByFilter(ProjectFilter filter) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
