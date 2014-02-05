package pt.altran.altranreq.manager.altranrequser;

import pt.altran.altranreq.entities.AltranreqUser;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.AltranreqUserBean;
import pt.altran.altranreq.services.UserService;

@Named(value = "altranreqUserEditController")
@ViewScoped
public class AltranreqUserEditController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private UserService ejbService;
    
    @Inject
    private AltranreqUserBean altranreqUserBean;
    
    private AltranreqUser altranreqUser;

    public AltranreqUserEditController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public void save(ActionEvent event) {
      try
      {
            setSelected(getAltranreqUser());
            ejbService.edit(this.getAltranreqUser());
          //   String successMsg = ResourceBundle.getBundle("/admin/altranreqUser").getString("Success_on_update");
            String successMsg = "Actualizado com Sucesso.";
            JsfUtil.addSuccessMessage(successMsg);
       } 
        catch (Exception e) 
        {
            String errorMsg = "Error";
            JsfUtil.addSuccessMessage(errorMsg);
        }
    }
    
   /*  @Override
    public void save(ActionEvent event) {

        FunctionalRequirement fr = (FunctionalRequirement) functionalRequirementBean.getSelected();
        setSelected(fr);
        functionalService.edit(fr);
        String successMsg = ResourceBundle.getBundle("MyBundle").getString("FunctionalRequirementUpdated");
        JsfUtil.addSuccessMessage(successMsg);
    }*/
    
    
    public AltranreqUser getAltranreqUser() {
        return (AltranreqUser) altranreqUserBean.getSelected();
    }
    
     public AltranreqUser setAltranreqUser() {
        return (AltranreqUser) altranreqUserBean.getSelected();
    }
}
