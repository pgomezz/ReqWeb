/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.manager.util.AltranTreeNode;

/**
 *
 * @author User
 */
@Named
@SessionScoped
//@Dependent
public class BusinessCategoryBean implements Serializable{
   
   //Project object
   @Context
   private Object selected;

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getSelected() {
        return selected;
    }
}
