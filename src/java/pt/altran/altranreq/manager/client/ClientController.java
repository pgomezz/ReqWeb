package pt.altran.altranreq.manager.client;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ClientBean;
import pt.altran.altranreq.services.ClientService;

@Named(value = "clientController")
@ViewScoped
public class ClientController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;
    
    @Inject
    private ClientBean clientBean;

    public ClientController() {
        super(Client.class);
    }
    
    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    public void keepState(){
        clientBean.setSelected(this.getSelected());
    }

}
