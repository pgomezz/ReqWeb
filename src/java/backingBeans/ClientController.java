package backingBeans;

import entities.Client;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.AbstractService;

@Named(value = "clientController")
@ViewScoped
public class ClientController extends AbstractController<Client> implements Serializable {

    @Inject
    private AbstractService ejbService;

    public ClientController() {
        super(Client.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
