package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
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
@XmlRootElement
public class FunctionalRequirementFilter implements Serializable{

    private String name;
    private Integer state;
    private Integer projecto;
    private Integer businessCategory;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getProjecto() {
        return projecto;
    }

    public void setProjecto(Integer projecto) {
        this.projecto = projecto;
    }

    public Integer getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(Integer businessCategory) {
        this.businessCategory = businessCategory;
    }

 


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
