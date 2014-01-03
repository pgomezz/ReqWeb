/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "beanPagina")
@SessionScoped
public class BeanPagina implements Serializable {

    private String paginaAdmin;
    private String paginaProj;
    private String accordTree;

    @PostConstruct
    public void init() {
        this.paginaAdmin="/admin/altranreqUser/List.xhtml";
        this.paginaProj="/project/List.xhtml";
        this.accordTree="/accordion.xhtml";
    }

    public String getPaginaProj() {
        return paginaProj;
    }

    public void setPaginaProj(String paginaProj) {
        this.paginaProj = paginaProj;
    }

    public void setPaginaAdmin(String paginaAdmin) {
        this.paginaAdmin = paginaAdmin;
    }

    public String getPaginaAdmin() {
        return paginaAdmin;
    }

    public String getAccordTree() {
        return accordTree;
    }

    public void setAccordTree(String accordTree) {
        this.accordTree = accordTree;
    }
    
}
