package pt.altran.altranreq.services;

import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.RNFType;
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
public class RNFunctionalFilter {
    public Project project;
    public String name;
    public RequirementState state;
    public RNFType type;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequirementState getState() {
        return state;
    }

    public void setState(RequirementState state) {
        this.state = state;
    }

    public RNFType getType() {
        return type;
    }

    public void setType(RNFType type) {
        this.type = type;
    }

 
    
}
