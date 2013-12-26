// BusinessCategory manage bean
package pt.altran.altranreq.manager.businesscategory;

import pt.altran.altranreq.entities.BusinessCategory;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.manager.AbstractController;
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
    
    public BusinessCategoryController() {
        super(BusinessCategory.class);
    }
    
    @PostConstruct
    public void init() {
        super.setService(businessCategoryService);
    }
    
    public void keepState(){
        businessCategoryBean.setSelected(this.getSelected());
    }

    public BusinessCategory getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(BusinessCategory businessCategory) {
        this.businessCategory = businessCategory;
    }

    @Override
    public void delete(ActionEvent event) {
        businessCategoryService.remove(getSelected());
        super.setItems(businessCategoryService.findAll());
    }
    
}
