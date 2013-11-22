package pt.altran.altranreq.services;
import pt.altran.altranreq.entities.AltranreqUser;

public interface AuthenticationService {

    public AltranreqUser Login(String name, String password);
    
    public boolean isAdmin(AltranreqUser User);
}