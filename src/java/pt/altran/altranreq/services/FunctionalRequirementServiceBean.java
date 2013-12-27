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
 * This bean has the same purpose as the RNFtServiceBean but is used to avoid
 * information conflicts
 *
 * @author User
 */
@Named
@SessionScoped
//@Dependent
public class FunctionalRequirementServiceBean implements Serializable {

    @Context
    private Object selected;
    
    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getSelected() {
        return selected;
    }
    
    public void updateUseCaseCollection(){
        
    }
}
