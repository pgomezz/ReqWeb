package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
import pt.altran.altranreq.services.builder.ProjectState;
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
    private Integer user;
    private String name;
    private Integer state;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}