package backingBeans;

import entities.AltranreqUser;
import sessionBeans.AltranreqUserFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "altranreqUserController")
@ViewScoped
public class AltranreqUserController extends AbstractController<AltranreqUser> implements Serializable {

    @Inject
    private AltranreqUserFacade ejbFacade;

    public AltranreqUserController() {
        super(AltranreqUser.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
