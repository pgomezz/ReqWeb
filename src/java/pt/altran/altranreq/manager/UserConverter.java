/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.manager;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.AltranreqUser;
import pt.altran.altranreq.services.UserService;
import pt.altran.altranreq.services.UserServiceImp;

/**
 *
 * @author francisco
 */
@Named("teste.convert")
public class UserConverter implements Converter {

    private AltranreqUser altranreqUser;
    private List<AltranreqUser> usersList;
    
    @Inject
    private UserService userService;

   
    
    @PostConstruct
    public void init(){
        this.usersList = userService.findAll();
    }
    
    
    public void setUsersList(ArrayList<AltranreqUser> usersList) {
        this.usersList = usersList;
    }
    
    public void setAltranreqUser(AltranreqUser altranreqUser) {
        this.altranreqUser = altranreqUser;
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        System.out.println("getAsObject");
             if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (AltranreqUser aru : usersList) {
                    if (aru.getIdUser().intValue() == number) {
                        return aru;
                    }
                }

            } catch(NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid player"));
            }
        }

        return null;
   
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        System.out.println("getAsString");
        if (value == null || value.equals("")) {  
            return "";  
        } else {  
            return ((AltranreqUser)value).getIdUser().toString();
            //return String.valueOf(((Player) value).getNumber());  
        }  
    }
    
}
