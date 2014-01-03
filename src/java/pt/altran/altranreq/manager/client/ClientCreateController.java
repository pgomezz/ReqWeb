package pt.altran.altranreq.manager.client;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ClientService;
import pt.altran.altranreq.services.OrganizationService;

@Named(value = "clientCreateController")
@ViewScoped
public class ClientCreateController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;
    
    @Inject
    private OrganizationService organizationService;
    
    private Client client;
    
    private Organization organization;

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
    
    public List<Organization> getOrganizationList(){
        List<Organization> listOrg = organizationService.findAll();
        return listOrg;
    }
    
    public Organization getOrganization(){
        return organization;
    }
    
    public void setOrganization(Organization org){
        String id = (org==null) ? null : org.getIdOrganization().toString();
        getClient().setIdOrganization(id);
        //organization = org;
    }
    
    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getClient());
        //getSelected().setOrganization(organization);
        ejbService.create(this.getSelected());
    }
}
