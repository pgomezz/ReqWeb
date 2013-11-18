package backingBeans;

import entities.Client;
import sessionBeans.ClientFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "clientController")
@ViewScoped
public class ClientController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientFacade ejbFacade;

    public ClientController() {
        super(Client.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
