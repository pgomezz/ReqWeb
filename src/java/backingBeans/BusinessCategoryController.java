package backingBeans;

import entities.BusinessCategory;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import sessionBeans.AbstractService;
import sessionBeans.AbstractServiceImp;
import sessionBeans.BusinessCategoryService;
import sessionBeans.BusinessCategoryServiceImp;

@Named(value = "businessCategoryController")
@ViewScoped
public class BusinessCategoryController extends AbstractController<BusinessCategory> implements Serializable {

    @Inject
    private BusinessCategoryService ejbService;

    public BusinessCategoryController() {
        super(BusinessCategory.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbService);
    }

}
