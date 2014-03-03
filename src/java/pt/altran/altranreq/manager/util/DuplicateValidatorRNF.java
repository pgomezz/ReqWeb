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
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFtServiceBean;
//import pt.altran.altranreq.entities.Project;
//import pt.altran.altranreq.services.ProjectService;
//import pt.altran.altranreq.services.ProjectServiceBean;

@FacesValidator("validator.duplicateValidatorRNF")
public class DuplicateValidatorRNF implements Validator {

    @Inject
    private RNFService RNFService;
    @Inject
    private RNFtServiceBean RNFServiceBean;
    @Inject 
    private ProjectServiceBean projBean;
    

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }
        String possibleRNFName = (String) value;
        
        BigDecimal bd = ((Project)projBean.getSelected()).getIdProject();
        int ia = bd.intValueExact();
        
        if (RNFServiceBean.getSelected() == null){
            isDuplicatedNFR(possibleRNFName, ia);
        } else if (!((NonFunctionalRequirement) RNFServiceBean.getSelected()).getName().equals(possibleRNFName)) {
            isDuplicatedNFR(possibleRNFName, ia);
        }
    }

    private void isDuplicatedNFR(String possibleFRName, int idProj) {
        if (RNFService.existNRFByName(possibleFRName, idProj)) {
                FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("/bundle_nfrType").getString("RNFDuplicado"));
                msg.setSeverity(SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
    }

}
