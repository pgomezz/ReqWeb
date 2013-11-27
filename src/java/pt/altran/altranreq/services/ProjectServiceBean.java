/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.services;

import java.io.Serializable;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Context;
import pt.altran.altranreq.manager.util.AltranTreeNode;

/**
 *
 * @author User
 */
@Named
@SessionScoped
public class ProjectServiceBean implements Serializable{

   @Context
   private Object selected;
   
   @Context
   private int idCategNRF;
   
   @Context
   private boolean Categ;
   
   @Context
   private AltranTreeNode root;

    public AltranTreeNode getRoot() {
        return root;
    }

    public void setRoot(AltranTreeNode root) {
        this.root = root;
    }

    public boolean isCateg() {
        return Categ;
    }

    public void setCateg(boolean Categ) {
        this.Categ = Categ;
    }

    public ProjectServiceBean() {
        selected = null;
        Categ = false;
        root = null;
    }

    public int getIdCategNRF() {
        return idCategNRF;
    }

    public void setIdCategNRF(int idCategNRF) {
        this.idCategNRF = idCategNRF;
    }

    public void setSelected(Object selected) {
        this.selected = selected;
    }

    public Object getSelected() {
        return selected;
    }
}
