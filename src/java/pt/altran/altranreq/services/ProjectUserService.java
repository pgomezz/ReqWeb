/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.ProjectUser;

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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
