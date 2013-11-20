package Converters;

import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.services.AbstractService;
import pt.altran.altranreq.manager.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import pt.altran.altranreq.services.FunctionalRequirementService;

@FacesConverter(value = "functionalRequirementConverter")
public class FunctionalRequirementConverter implements Converter {

    @Inject
    private FunctionalRequirementService ejbService;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbService.find(getKey(value));
    }

    java.math.BigDecimal getKey(String value) {
        java.math.BigDecimal key;
        key = new java.math.BigDecimal(value);
        return key;
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
        if (object instanceof FunctionalRequirement) {
            FunctionalRequirement o = (FunctionalRequirement) object;
            return getStringKey(o.getIdFunctionalRequirement());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), FunctionalRequirement.class.getName()});
            return null;
        }
    }

}
