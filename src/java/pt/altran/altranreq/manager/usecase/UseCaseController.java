package pt.altran.altranreq.manager.usecase;

import java.io.IOException;
import pt.altran.altranreq.entities.UseCase;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.TreeService;
import pt.altran.altranreq.services.UseCaseBean;
import pt.altran.altranreq.services.UseCaseService;

@Named(value = "useCaseController")
@ViewScoped
public class UseCaseController extends AbstractController<UseCase> implements Serializable {

    @Inject
    private UseCaseService ejbService;

    @Inject
    private TreeService treeService;
    
    @Inject
    private FunctionalRequirementService functReqService;

    @Inject
    private FunctionalRequirementServiceBean funcReqBean;

    @Inject
    private UseCaseBean useCaseBean;

    private UseCase useCase;

    public UseCaseController() {
        super(UseCase.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }

    public boolean isUseCaseType() {
        return treeService.getSelected() instanceof UseCase;
    }

    public boolean isUseCaseTypeByNavigation() {
        return useCaseBean.getSelected() instanceof UseCase;
    }

    public UseCase getUseCase() {
        return (UseCase) treeService.getSelected();
    }

    public UseCase getUseCaseByNavigation() {
        return (UseCase) useCaseBean.getSelected();
    }

    public List<UseCase> getLista() {
        FunctionalRequirement funcReqSelected = (FunctionalRequirement) funcReqBean.getSelected();
        
        int idfuncReq = Integer.parseInt(funcReqSelected.getIdFunctionalRequirement().toString());
        //System.out.println("Lista = "+ Integer.parseInt(funcReqSelected.getIdFunctionalRequirement().toString()));
        return ejbService.findUseCaseByRequirement(idfuncReq);
    }

    @Override
    public void delete(ActionEvent event) {
        UseCase pj = (UseCase) useCaseBean.getSelected();
        setSelected(pj);
        ejbService.remove(pj);
    }

    public void redirect(int action) throws IOException {
        if (action == 1) {
            useCase = super.prepareCreate(null);
        } else {
            useCaseBean.setSelected(this.getSelected());
        }
    }
}