/*
 * Validates duplicated values
 */
package pt.altran.altranreq.manager.util;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import static javax.faces.application.FacesMessage.SEVERITY_ERROR;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;

@FacesValidator("validator.duplicateValidator")
public class DuplicateValidator implements Validator {

    @Inject
    private ProjectService projectService;
    @Inject
    private ProjectServiceBean projectServiceBean;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null) {
            return;
        }

        String possibleProjectName = (String) value;
        if (!((Project) projectServiceBean.getSelected()).getName().equals(possibleProjectName)) {
            if (projectService.existProjectByName(possibleProjectName)) {
                FacesMessage msg = new FacesMessage(ResourceBundle.getBundle("/project").getString("DuplicateName_Project"));
                msg.setSeverity(SEVERITY_ERROR);
                throw new ValidatorException(msg);
            }
        }
    }

}
