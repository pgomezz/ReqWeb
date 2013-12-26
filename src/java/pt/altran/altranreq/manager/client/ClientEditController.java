package pt.altran.altranreq.manager.client;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ClientBean;
import pt.altran.altranreq.services.ClientService;

@Named(value = "clientEditController")
@ViewScoped
public class ClientEditController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;
    
    @Inject
    private ClientBean clientBean;
    
    private Client client;

    public ClientEditController() {
        super(Client.class);
    }
    
    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public void save(ActionEvent event) {
        //setSelected(getSelected());
        ejbService.edit(this.getClient());
        
    }

    public Client getClient() {
        return (Client) clientBean.getSelected();
    }
    

}
