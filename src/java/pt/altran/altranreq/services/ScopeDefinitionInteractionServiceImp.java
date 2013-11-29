//ScopeDefinitionInteraction for v2 Implemantion
package pt.altran.altranreq.services;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pt.altran.altranreq.entities.ScopeDefinitionInteraction;

@Stateless
public class ScopeDefinitionInteractionServiceImp extends AbstractServiceImp<ScopeDefinitionInteraction> implements ScopeDefinitionInteractionService {
    @PersistenceContext(unitName = "AltranReqPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /*public ScopeDefinitionInteractionServiceImp() {
        super(ScopeDefinitionInteraction.class);
    }*/

    @Override
    public List<ScopeDefinitionInteraction> findType(String type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
