package Converters;

import pt.altran.altranreq.manager.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJBException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.services.UserService;

@Named(value = "analystConverter")
@RequestScoped
public class AnalystConverter implements Converter {

    @Inject
    private UserService ejbService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        AltranreqUser user = this.ejbService.find(getKey(value));
        return user;
    }

    java.math.BigDecimal getKey(String value) {
        try {
            java.math.BigDecimal key;
            key = new java.math.BigDecimal(value);
            return key;
        } catch (Exception e) {
            return null;
        }

    }

    String getStringKey(java.math.BigDecimal value) {
        StringBuffer sb = new StringBuffer();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null
                || (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof AltranreqUser) {
            AltranreqUser o = (AltranreqUser) object;
            return getStringKey(o.getIdUser());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AltranreqUser.class.getName()});
            return null;
        }
    }

}
