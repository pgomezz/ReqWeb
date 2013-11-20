package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import pt.altran.altranreq.manager.builder.ProjectState;
import pt.altran.altranreq.entities.ProjectUser;

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
public class ProjectFilter implements Serializable{
    private ProjectUser user;
    private String name;
    private ProjectState state;

    public ProjectUser getUser() {
        return user;
    }

    public void setUser(ProjectUser user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }
    
    
}
