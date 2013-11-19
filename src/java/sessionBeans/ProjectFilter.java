package sessionBeans;

import builder.ProjectState;
import entities.ProjectUser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




/**
 *
 * @author User
 */
public class ProjectFilter {
    public ProjectUser user;
    public String name;
    public ProjectState state;

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
