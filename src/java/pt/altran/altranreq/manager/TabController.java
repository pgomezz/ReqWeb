/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.component.tabview.TabView;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Consultor
 */
@Named
@SessionScoped
public class TabController implements Serializable {

    private int activeTab = 0;

    public int getActiveTab() {
        return activeTab;
    }

    public void onTabChange(TabChangeEvent event) {
        TabView tabView = (TabView) event.getComponent();
        this.activeTab = tabView.getChildren().indexOf(event.getTab());
    }

}
