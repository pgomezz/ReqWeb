package pt.altran.altranreq.manager;

import pt.altran.altranreq.entities.BusinessCategory;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.inject.Inject;
import javax.faces.view.ViewScoped;
import pt.altran.altranreq.services.AbstractService;
import pt.altran.altranreq.services.AbstractServiceImp;
import pt.altran.altranreq.services.BusinessCategoryService;
import pt.altran.altranreq.services.BusinessCategoryServiceImp;

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
        super.setService(ejbService);
    }

}
