package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.AlternativeFlows;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateful
public class AlternativeFlowsImp extends AbstractServiceImp<AlternativeFlows> implements AlternativeFlowsService {

            @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct 
    public void init() {
     setEntityClass(AlternativeFlows.class);
    }
    
    @Override
    public List<AlternativeFlows> findAlternativeFlowsByUseCase(AlternativeFlows alternativeFlows) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
    
}
