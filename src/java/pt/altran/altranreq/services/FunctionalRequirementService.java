package pt.altran.altranreq.services;


import pt.altran.altranreq.entities.AltranreqRole;
import pt.altran.altranreq.entities.BusinessCategory;
import pt.altran.altranreq.entities.FunctionalRequirement;
import java.util.List;
import pt.altran.altranreq.services.AbstractService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 * @param <T>
 */
public interface FunctionalRequirementService extends AbstractService<FunctionalRequirement> {
    
    
    List<FunctionalRequirement> findFunctionalRequirementByFilter(FunctionalRequirementFilter filter);
}
