/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.services;

import pt.altran.altranreq.entities.Organization;

/**
 *
 * @author User
 */
public interface OrganizationService extends AbstractService<Organization> {

    public Organization getOrganizationById(String id);
    
}
