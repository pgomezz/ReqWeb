package pt.altran.altranreq.manager.altranrequser;

import java.io.IOException;
import pt.altran.altranreq.entities.AltranreqUser;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.AltranreqUserBean;
import pt.altran.altranreq.services.UserService;

@Named(value = "altranreqUserController")
@ViewScoped
public class AltranreqUserController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private UserService ejbService;
    
    @Inject
    private AltranreqUserBean altranreqUserBean;
    
    private AltranreqUser altranreqUser;

    public AltranreqUserController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }
    
    @Override
    public List<AltranreqUser> getItems() {
        return super.getItems();
    }
    
    public void keepState() throws IOException {
            altranreqUserBean.setSelected(this.getSelected());
    }
    @Override
    public void delete(ActionEvent event) {
        try {
            ejbService.remove(this.getSelected());
            super.setItems(ejbService.findAll());
            sendMessages(FacesMessage.SEVERITY_INFO, ResourceBundle.getBundle("/user").getString("RemoveSuccessMessage"), null);
        } catch (Exception e) {
            sendMessages(FacesMessage.SEVERITY_ERROR, ResourceBundle.getBundle("/user").getString("ErrorMessage"), null);
        }
    }
    
    public void sendMessages(FacesMessage.Severity severity, String summary, String details) {
        FacesMessage message = new FacesMessage(severity, summary, details);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
