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

    @PostConstruct
    public void init() {
        this.paginaAdmin="/admin/altranreqUser/List.xhtml";
        this.paginaProj="/project/List.xhtml";
    }

    public String getPaginaProj() {
        System.out.println("PAGINA Proj GET: "+paginaProj);
        return paginaProj;
    }

    public void setPaginaProj(String paginaProj) {
        System.out.println("PAGINA Proj SET");
        this.paginaProj = paginaProj;
    }

    public void setPaginaAdmin(String paginaAdmin) {
        System.out.println("PAGINA Admin SET");
        this.paginaAdmin = paginaAdmin;
    }

    public String getPaginaAdmin() {
        System.out.println("PAGINA Admin GET: "+paginaAdmin);
        return paginaAdmin;
    }
}
