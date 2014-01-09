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
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;

/**
 *
 * @author francisco
 */
@Named(value = "bigDecimalConverterAnalyst")
@ViewScoped
public class BigDecimalConverterAnalyst implements Converter{

    @Inject
    private AnalystConverter analyst;

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        AltranreqUser obj = (AltranreqUser) analyst.getAsObject(context, component, value);
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof String)return (String) value;
        return ((AltranreqUser)value).getIdUser().toString();
    }
    
}
