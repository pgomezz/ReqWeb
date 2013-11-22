package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.AlternativeFlows;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 */
public interface AlternativeFlowsService extends AbstractService<AlternativeFlows> {
    
    public List<AlternativeFlows> findAlternativeFlowsByUseCase(int idUseCase);

}
