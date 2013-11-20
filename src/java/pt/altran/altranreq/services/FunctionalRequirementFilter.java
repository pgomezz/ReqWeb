package pt.altran.altranreq.services;

import pt.altran.altranreq.entities.BusinessCategory;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.RequirementState;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class FunctionalRequirementFilter {

    public String name;
    public RequirementState state;
    public Project projecto;
    public BusinessCategory businessCategory;

    public RequirementState getState() {
        return state;
    }

    public void setState(RequirementState state) {
        this.state = state;
    }

    public Project getProjecto() {
        return projecto;
    }

    public void setProjecto(Project projecto) {
        this.projecto = projecto;
    }

    public BusinessCategory getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(BusinessCategory businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
