
package pt.altran.altranreq.manager.util;

import java.util.Date;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validator.dateRangeValidator")
public class DateRangeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || component.getAttributes().get("fromDate") == null) return;

        System.out.println("estou aki!");
        
        Date toDate   = (Date) value; 
        Date fromDate = (Date) component.getAttributes().get("fromDate");
        
        System.out.println("todate:"+toDate.toString());
        System.out.println("fromdate:"+fromDate.toString());

        if (fromDate.after(toDate)) {
            FacesMessage message = new FacesMessage(ResourceBundle.getBundle("/project").getString("InvalidDatesRange"));
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(message);
        }
    }
}