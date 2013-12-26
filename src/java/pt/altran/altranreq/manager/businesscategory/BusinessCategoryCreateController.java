/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.manager.businesscategory;


import java.io.Serializable;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pt.altran.altranreq.entities.BusinessCategory;
import pt.altran.altranreq.manager.AbstractController;
import pt.altran.altranreq.manager.util.JsfUtil;
import pt.altran.altranreq.services.BusinessCategoryService;

/**
 *
 * @author User
 */
@Named(value= "businessCategoryCreateController")
@ViewScoped
public class BusinessCategoryCreateController extends AbstractController<BusinessCategory> implements Serializable{

    @Inject
    private BusinessCategoryService businessCategoryService;
    
    private BusinessCategory businessCategory;
    
    public BusinessCategoryCreateController() {
        super(BusinessCategory.class);
    }
    
    @PostConstruct
    public void init(){
        super.setService(businessCategoryService);
        super.setSelected(new BusinessCategory());
    }

    @Override
    public void saveNew(ActionEvent event) {
        setSelected(getBusinessCategory());
        businessCategoryService.create(getBusinessCategory());
        String successMsg = ResourceBundle.getBundle("/business").getString("Success_on_create");
        JsfUtil.addSuccessMessage(successMsg);
    }

    public BusinessCategory getBusinessCategory() {
        BusinessCategory sel = getSelected();
        return sel;
    }
    
}
