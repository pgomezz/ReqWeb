package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.UseCase;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.services.FunctionalRequirementServiceBean;
import pt.altran.altranreq.services.TreeService;
import pt.altran.altranreq.services.UseCaseBean;
import pt.altran.altranreq.services.UseCaseService;

@Named(value = "editUseCaseManager")
@ViewScoped
public class EditUseCaseManager extends AbstractController<UseCase> implements Serializable {

    @Inject
    private UseCaseService ejbService;
    
    @Inject
    private TreeService treeService;
    
    @Inject
    private FunctionalRequirementServiceBean funcReqBean;
    
    @Inject
    private UseCaseBean useCaseBean;

    public EditUseCaseManager() {
        super(UseCase.class);
    }

    @PostConstruct
    public void init() {
        super.setService(ejbService);
    }
    
    public boolean isUseCaseType()
    {
        return treeService.getSelected() instanceof UseCase;
    }
    
    public boolean isUseCaseTypeByNavigation()
    {
        return useCaseBean.getSelected() instanceof UseCase;
    }
    
    public UseCase getUseCase()
    {
        return (UseCase)treeService.getSelected();
    }
    
    public UseCase getUseCaseByNavigation()
    {
        return (UseCase)useCaseBean.getSelected();
    }
    
    public List<UseCase> getLista()
    {
        FunctionalRequirement funcReqSelected = (FunctionalRequirement)funcReqBean.getSelected();
        
        int idfuncReq = Integer.parseInt(funcReqSelected.getIdFunctionalRequirement().toString());
        return ejbService.findUseCaseByRequirement(idfuncReq);
    }

    @Override
    public void save(ActionEvent event) {
        UseCase uc = (UseCase) useCaseBean.getSelected();
        setSelected(uc);
        ejbService.edit(uc);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/useCase/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(EditUseCaseManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public void redirect() throws IOException {
        useCaseBean.setSelected(this.getSelected());
    }
}
