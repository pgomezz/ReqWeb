
package pt.altran.altranreq.services;


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;

/**
 *
 * @author Consultor
 */
@Named
@SessionScoped
public class UserServiceBean implements Serializable{

    
    @Context
   private Object selected;
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public Object getSelected() {
        return selected;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }
}







   