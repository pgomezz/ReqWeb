package pt.altran.altranreq.manager;

import java.io.IOException;
import pt.altran.altranreq.entities.BusinessCategory;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.BusinessCategoryBean;
import pt.altran.altranreq.services.BusinessCategoryService;

@Named(value = "businessCategoryController")
@ViewScoped
public class BusinessCategoryController extends AbstractController<BusinessCategory> implements Serializable {

    @Inject
    private BusinessCategoryService businessCategoryService;
    
    @Inject
    private BusinessCategoryBean businessCategoryBean;
    
    private BusinessCategory businessCategory;

    public BusinessCategory getBusinessCategory() {
        return (BusinessCategory)businessCategoryBean.getSelected();
    }

    public void setBusinessCategory(BusinessCategory businessCategory) {
        this.businessCategory = businessCategory;
    }
    
   
    
    public BusinessCategoryController() {
        super(BusinessCategory.class);
    }

    @Override
    public void saveNew(ActionEvent event) {
        setSelected(businessCategory);
        businessCategoryService.create(businessCategory);
    }

    @Override
    public void save(ActionEvent event) {
               
        BusinessCategory bc = (BusinessCategory) businessCategoryBean.getSelected();
        setSelected(bc);
        businessCategoryService.edit(bc);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/admin/businessCategory/index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(BusinessCategoryController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    

    public BusinessCategoryService getBusinessCategoryService() {
        return businessCategoryService;
    }

    public void setBusinessCategoryService(BusinessCategoryService businessCategoryService) {
        this.businessCategoryService = businessCategoryService;
    }
    

    @PostConstruct
    public void init() {
        super.setService(businessCategoryService);
        businessCategory = super.prepareCreate(null);
    }

    public void redirect() throws IOException
    {
        businessCategoryBean.setSelected(this.getSelected());
        //ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        //externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/admin/businessCategory/Edit.xhtml");
    }
}
