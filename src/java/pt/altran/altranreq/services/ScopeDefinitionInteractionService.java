/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import java.util.List;
import javax.ejb.Local;
import pt.altran.altranreq.entities.ScopeDefinitionInteraction;

/**
 *
 * @author User
 */
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
