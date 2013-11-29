package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import pt.altran.altranreq.entities.NonFunctionalRequirement;
import java.util.List;

/**
 *
 * @author User
 * @param <T>
 */
public interface RNFService extends AbstractService<NonFunctionalRequirement> {
    
    public List<NonFunctionalRequirement> findRNFByFilter(RNFunctionalFilter filter);

    
    public List<NonFunctionalRequirement> findRNFByDependency(int pai);
    
    String getRequirementStateString(int requirementStateIndice);
    String getRequirementTypeString(int requirementTypeIndice);
}
