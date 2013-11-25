package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.services.ActorService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "actorController")
@ViewScoped
public class ActorController extends AbstractController<Actor> implements Serializable {

    @Inject
    private ActorService ejbService;

    public ActorController() {
        super(Actor.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

}
