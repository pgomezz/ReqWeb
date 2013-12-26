package pt.altran.altranreq.manager.client;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ClientService;

@Named(value = "clientCreateController")
@ViewScoped
public class ClientCreateController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;
    
    private Client client;

    public ClientCreateController() {
        super(Client.class);
    }
    
    @PostConstruct
    public void init() {
        super.setService(ejbService);
        super.setSelected(new Client());
    }

    public Client getClient() {
        Client sel = getSelected();
        return sel;
    }
    
    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getClient());
        ejbService.create(this.getSelected());
    }
    
    
}
