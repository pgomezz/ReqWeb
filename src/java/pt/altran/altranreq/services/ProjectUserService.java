/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.ProjectUser;
import pt.altran.altranreq.entities.ProjectUserPK;

/**
 *
 * @author User
 */
@Stateless
public class ProjectUserService extends AbstractServiceImp<ProjectUser> {
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @PostConstruct
    public void init() {
        setEntityClass(ProjectUser.class);
        //super(ProjectUser.class);
    }
    
    public ProjectUser getProjectUser(BigDecimal idProject, BigDecimal idUser){
        
        Query queryPU = em.createNamedQuery("ProjectUser.findByIdProjIdUser");
        queryPU.setParameter("idProject",idProject);
        queryPU.setParameter("idUser",idUser);
        ProjectUser PU = (ProjectUser) queryPU.getSingleResult();
        return PU;
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
