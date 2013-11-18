package backingBeans;

import entities.Actor;
import sessionBeans.ActorFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "actorController")
@ViewScoped
public class ActorController extends AbstractController<Actor> implements Serializable {

    @Inject
    private ActorFacade ejbFacade;

    public ActorController() {
        super(Actor.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
