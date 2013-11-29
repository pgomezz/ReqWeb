/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;

/**
 *
 * Bean used to store the selected information on the List, in order to be able
 * to use it to view the details and to edit the information.
 */
@Named
@SessionScoped
public class RNFtServiceBean implements Serializable{

   @Context
   private Object selected;
   
  
    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getSelected() {
        return selected;
    }
}
