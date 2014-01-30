/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author User
 */
@Named(value = "beanCaminho")
@SessionScoped
public class BeanCaminho implements Serializable {

    @Inject
    private BeanPagina beanPagina;

    private String caminhoAdmin;
    private String caminhoProj;
    private String valueAdmin;
    private String valueProj;

    @PostConstruct
    public void init() {

    }

    public void gerarCaminho(){
        switch (beanPagina.getPaginaAdmin()) {
            case "/admin/altranreqUser/List.xhtml":
                //System.out.println("INDEX");
                setValueAdmin("Index");
                setCaminhoAdmin("<p:commandLink value=\"Index>\" actionListener=\"/admin/altranreqUser/List.xhtml\"/>");
                break;
            case "/admin/altranreqUser/Create.xhtml":
                System.out.println("INDEX>Utilizador>Conta>Criar");
                break;
            case "/admin/altranreqUser/Edit.xhtml":
                System.out.println("INDEX>Utilizador>Conta>Editar");
                break;
            case "/admin/altranreqUser/View.xhtml":
                System.out.println("INDEX>Utilizador>Conta>Detalhes");
                break;
            default:
                System.out.println("OUTRO");
        }
    }
    public String getCaminhoAdmin() {
        return caminhoAdmin;
    }

    public void setCaminhoAdmin(String caminhoAdmin) {
        this.caminhoAdmin = caminhoAdmin;
    }
    
    public void setValueAdmin(String value){
        this.valueAdmin=value;
    }
    
    public String getValueAdmin(){
        return this.valueAdmin;
    }

    public String getCaminhoProj() {
        return caminhoProj;
    }

    public void setCaminhoProj(String caminhoProj) {
        this.caminhoProj = caminhoProj;
    }

    public String getValueProj() {
        return valueProj;
    }

    public void setValueProj(String valueProj) {
        this.valueProj = valueProj;
    }
}
