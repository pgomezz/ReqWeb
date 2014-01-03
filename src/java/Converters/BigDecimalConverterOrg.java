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
import pt.altran.altranreq.entities.Organization;

/**
 *
 * @author francisco
 */
@Named(value = "bigDecimalConverterOrg")
@ViewScoped
public class BigDecimalConverterOrg implements Converter{

    @Inject
    private OrganizationConverter organization;

    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Organization obj = (Organization) organization.getAsObject(context, component, value);
        return obj;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof String)return (String) value;
        return ((Organization)value).getIdOrganization().toString();
    }
    
}
