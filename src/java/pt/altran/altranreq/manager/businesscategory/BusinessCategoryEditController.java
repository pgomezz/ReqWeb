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

@Named(value = "businessCategoryEditController")
@ViewScoped
public class BusinessCategoryEditController extends AbstractController<BusinessCategory> implements Serializable {

    @Inject
    private BusinessCategoryService businessCategoryService;
    
    @Inject
    private BusinessCategoryBean businessCategoryBean;
    
    private BusinessCategory businessCategory;
    
    public BusinessCategoryEditController() {
        super(BusinessCategory.class);
    }
    
    @PostConstruct
    public void init() {
        super.setService(businessCategoryService);
    }

    @Override
    public void save(ActionEvent event) {
        businessCategoryService.edit(getBusinessCategory());
    }

    public BusinessCategory getBusinessCategory() {
        return (BusinessCategory) businessCategoryBean.getSelected();
    }
    
    
}
