package pt.altran.altranreq.services;
import pt.altran.altranreq.entities.AltranreqUser;

public interface AuthenticationService {

    public AltranreqUser login(String username, String password);
   
}
