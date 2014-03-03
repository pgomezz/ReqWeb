/*
 * Validates duplicated values
 */
package pt.altran.altranreq.manager.util;

import java.math.BigDecimal;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;
//import pt.altran.altranreq.entities.Project;
//import pt.altran.altranreq.services.ProjectService;
//import pt.altran.altranreq.services.ProjectServiceBean;

@FacesValidator("validator.duplicateValidatorRF")
public class DuplicateValidatorRF implements Validator {

    @Inject
    private FunctionalRequirementService FRService;
    @Inject
    private FunctionalRequirementServiceBean FRServiceBean;
    @Inject ProjectServiceBean projBean;
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String possibleFRName = (String) value;
        
        BigDecimal bd = ((Project)projBean.getSelected()).getIdProject();
        int ia = bd.intValueExact();
        
        if (FRServiceBean.getSelected() == null){
            isDuplicatedFR(possibleFRName, ia);
        } else if (!((FunctionalRequirement) FRServiceBean.getSelected()).getName().equals(possibleFRName)) {
            isDuplicatedFR(possibleFRName, ia);
        }
    }

    private void isDuplicatedFR(String possibleFRName, int idProj) {
        if (FRService.existRFByName(possibleFRName, idProj)) {
                FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("/bundle_requirement").getString("FRDuplicado"));
                msg.setSeverity(SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
    }

}
