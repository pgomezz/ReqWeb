/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessionBeans;

import entities.AltranreqRole;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class AltranreqRoleFacade extends AbstractFacade<AltranreqRole> {
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AltranreqRoleFacade() {
        super(AltranreqRole.class);
    }
    
}
