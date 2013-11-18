package backingBeans;

import entities.BusinessCategory;
import sessionBeans.BusinessCategoryFacade;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;

@Named(value = "businessCategoryController")
@ViewScoped
public class BusinessCategoryController extends AbstractController<BusinessCategory> implements Serializable {

    @Inject
    private BusinessCategoryFacade ejbFacade;

    public BusinessCategoryController() {
        super(BusinessCategory.class);
    }

    @PostConstruct
    public void init() {
        super.setFacade(ejbFacade);
    }

}
