/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.TabChangeEvent;

/**
 *
 * @author Consultor
 */
@Named(value = "tabBean")
@SessionScoped
public class TabController implements Serializable {

    private int activeIndex;// = 1;

    public int getActiveIndex() {
        System.out.println("Index "+ activeIndex);
        return activeIndex;
    }

    public void setActiveIndex(int activeIndex) {
        this.activeIndex = activeIndex;
    }

    public void setProjectTab() {
        System.out.println("ProjectTab**Active index:" + this.activeIndex);
        this.activeIndex=1;
        System.out.println("ProjectTab**Active index:" + this.activeIndex);
    }

    public void setAdminTab() {
        System.out.println("AdminTab***Active index:" + this.activeIndex);
        this.activeIndex=0;
        System.out.println("AdminTab***Active index:" + this.activeIndex);
    }

    public void onTabChange(TabChangeEvent event) {
        if(event.getTab().getTitle().equals("Projetos")){
            this.activeIndex=1;
        }else{
            this.activeIndex=0;
        }
        System.out.println("************Active index:" + this.activeIndex);
    }
}
