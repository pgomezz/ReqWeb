/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Converters;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;

/**
 *
 * @author francisco
 */
@Named(value = "bigDecimalConverter")
@RequestScoped
public class BigDecimalConverter implements Converter{

    @Inject
    private AltranreqUserConverter altranreqUser;

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        AltranreqUser obj = (AltranreqUser) altranreqUser.getAsObject(context, component, value);
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof String)return (String) value;
        return ((AltranreqUser)value).getIdUser().toString();
    }
    
}
