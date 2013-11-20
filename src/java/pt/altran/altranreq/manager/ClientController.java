package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.ClientService;

@Named(value = "clientController")
@ViewScoped
public class ClientController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;

    public ClientController() {
        super(Client.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
