/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Consultor
 */
@Named(value = "TabBean")
@ViewScoped
public class TabController implements Serializable {

    private String activeIndex = "0";

    public TabController() {

    }

    public String getActiveIndex() {
        return activeIndex;
    }

    public void setProjectTab() {
        this.activeIndex = "0";
    }

    public void setAdminTab() {
        this.activeIndex = "1";
    }

    public void onTabChange(TabChangeEvent event) {
        this.activeIndex = event.getTab().getId();
    }
}
