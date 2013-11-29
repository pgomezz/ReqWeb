//ScopeDefinitionInteraction v2 Implemantion
package pt.altran.altranreq.services;

import java.util.List;
import javax.ejb.Local;
import pt.altran.altranreq.entities.ScopeDefinitionInteraction;

@Local
public interface ScopeDefinitionInteractionService {

    void create(ScopeDefinitionInteraction scopeDefinitionInteraction);

    void edit(ScopeDefinitionInteraction scopeDefinitionInteraction);

    void remove(ScopeDefinitionInteraction scopeDefinitionInteraction);

    ScopeDefinitionInteraction find(Object id);

    List<ScopeDefinitionInteraction> findAll();

    List<ScopeDefinitionInteraction> findRange(int[] range);

    int count();
    
    List<ScopeDefinitionInteraction> findType(String type);
}
