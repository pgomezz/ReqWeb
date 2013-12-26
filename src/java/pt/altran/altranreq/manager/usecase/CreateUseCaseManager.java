package pt.altran.altranreq.manager.usecase;

import pt.altran.altranreq.entities.UseCase;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.UseCaseService;

@Named(value = "createUseCaseManager")
@ViewScoped
public class CreateUseCaseManager extends AbstractController<UseCase> implements Serializable {

    @Inject
    private UseCaseService ejbService;

    @Inject
    private FunctionalRequirementServiceBean funcReqBean;

    private UseCase useCase;

    @PostConstruct
    public void init() {
        super.setService(ejbService);
        super.setSelected(new UseCase());
    }

    public UseCase getUseCase() {
        return getSelected();
    }

    @Override
    public void saveNew(ActionEvent event) {
        getUseCase().setIdFunctionalRequirement((FunctionalRequirement) funcReqBean.getSelected());
        setSelected(getUseCase());
        ejbService.create(getUseCase());
    }
}