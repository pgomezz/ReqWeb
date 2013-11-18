package Converters;

import entities.ProjectUser;
import sessionBeans.ProjectUserFacade;
import backingBeans.util.JsfUtil;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "projectUserConverter")
public class ProjectUserConverter implements Converter {

    @Inject
    private ProjectUserFacade ejbFacade;

    private static final String SEPARATOR = "#";
    private static final String SEPARATOR_ESCAPED = "\\#";

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0 || JsfUtil.isDummySelectItem(component, value)) {
            return null;
        }
        return this.ejbFacade.find(getKey(value));
    }

    entities.ProjectUserPK getKey(String value) {
        entities.ProjectUserPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.ProjectUserPK();
            key.setIdUser(new BigDecimal(values[0]));
            key.setIdProject(new BigDecimal(values[1]));
        return key;
    }

    String getStringKey(entities.ProjectUserPK value) {
        StringBuffer sb = new StringBuffer();
            sb.append(value.getIdUser());
            sb.append(SEPARATOR);
            sb.append(value.getIdProject());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null || 
            (object instanceof String && ((String) object).length() == 0)) {
            return null;
        }
        if (object instanceof ProjectUser) {
            ProjectUser o = (ProjectUser) object;
            return getStringKey(o.getProjectUserPK());
        } else {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ProjectUser.class.getName()});
            return null;
        }
    }

}
