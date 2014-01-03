package pt.altran.altranreq.manager.client;

import pt.altran.altranreq.entities.Client;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.Organization;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.ClientBean;
import pt.altran.altranreq.services.ClientService;
import pt.altran.altranreq.services.OrganizationService;

@Named(value = "clientEditController")
@ViewScoped
public class ClientEditController extends AbstractController<Client> implements Serializable {

    @Inject
    private ClientService ejbService;
    
    @Inject
    private OrganizationService organizationService;
    
    @Inject
    private ClientBean clientBean;
    
    private Client client;

    /*public ClientEditController() {
        super(Client.class);
    }*/
    
    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    @Override
    public void save(ActionEvent event) {
        //setSelected(getSelected());
        System.out.println("ID da Organização1: "+this.getClient().getIdOrganization());
        ejbService.edit(this.getClient());
        System.out.println("ID da Organização2: "+this.getClient().getIdOrganization());
    }

    public Client getClient() {
        return (Client)clientBean.getSelected();
    }
    
    public List<Organization> getOrganizationList(){
        List<Organization> listOrg = organizationService.findAll();
        return listOrg;
    }
    
    public Organization getOrganization(){
        return clientBean.getOrganization();
    }
    
    public void setOrganization(Organization org){
        String id = (org==null) ? null : org.getIdOrganization().toString();
        clientBean.setIdOrganization(id);
        //clientBean.setOrganization(org);
    }
    
    public String getOrganizationNameById(){
        if(clientBean.getIdOrganization()!=null){
        Organization org = clientBean.getOrganization();
        return org.getName();
        }
        else{
            return "Sem organização";
        }
    }
}
