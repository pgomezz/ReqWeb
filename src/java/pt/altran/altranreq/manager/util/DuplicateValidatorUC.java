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
import pt.altran.altranreq.entities.UseCase;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFtServiceBean;
import pt.altran.altranreq.services.UseCaseBean;
import pt.altran.altranreq.services.UseCaseService;
//import pt.altran.altranreq.entities.Project;
//import pt.altran.altranreq.services.ProjectService;
//import pt.altran.altranreq.services.ProjectServiceBean;

@FacesValidator("validator.duplicateValidatorUC")
public class DuplicateValidatorUC implements Validator {

    @Inject
    private UseCaseService UCService;
    @Inject
    private UseCaseBean UCServiceBean;
    @Inject 
    private FunctionalRequirementServiceBean FRBean;
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String possibleUCName = (String) value;
        
        BigDecimal bd = ((FunctionalRequirement)FRBean.getSelected()).getIdFunctionalRequirement();
        int ia = bd.intValueExact();
        
        if (UCServiceBean.getSelected() == null){
            isDuplicatedUC(possibleUCName, ia);
        } else if (!((UseCase) UCServiceBean.getSelected()).getName().equals(possibleUCName)) {
            isDuplicatedUC(possibleUCName, ia);
        }
    }

    private void isDuplicatedUC(String possibleUCName, int idProj) {
        if (UCService.existUseCaseByName(possibleUCName, idProj)) {
                FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("/useCase").getString("UCDuplicado"));
                msg.setSeverity(SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
    }

}
