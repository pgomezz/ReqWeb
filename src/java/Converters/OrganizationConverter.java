package Converters;

import pt.altran.altranreq.manager.util.JsfUtil;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.services.OrganizationService;

@Named(value = "organizationConverter")
@RequestScoped
public class OrganizationConverter implements Converter {

    @Inject
    private OrganizationService ejbService;
    

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {           
            return null;
        }
        Organization organization = this.ejbService.find(getKey(value));
        return organization;
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
        if (object instanceof Organization) {
            Organization o = (Organization) object;
            return getStringKey(o.getIdOrganization());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Organization.class.getName()});
            return null;
        }
    }

}
