/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.altran.altranreq.manager.util;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;

/**
 *
 * @author User
 */
public class AltranTreeNode extends DefaultTreeNode {

    private TreeNode currentNode = this;

    public UpdateCurrentTreeNode treenode;

    public AltranTreeNode(Object data, TreeNode parent) {
        super(data, parent);
    }

    @Override
    public Object getData() {
        treenode.update(this);
        System.out.println("passou " + super.getData());
        Object d = super.getData();
        if (d instanceof UseCase) {
            UseCase uc = (UseCase) d;
            return uc.getName();
        } else if (d instanceof FunctionalRequirement) {
            FunctionalRequirement fr = (FunctionalRequirement) d;
            return fr.getName();
        } else if (d instanceof NonFunctionalRequirement) {
            NonFunctionalRequirement nfr = (NonFunctionalRequirement) d;
            return nfr.getName();
        } else {
            return super.getData(); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public Object getRealData() {
        return super.getData();
    }

    public void setTreenode(UpdateCurrentTreeNode treenode) {
        this.treenode = treenode;
    }

    public TreeNode getCurrent() {
        return currentNode;
    }

    @Override
    public String toString() {
        Object d = super.getData();
        if (d instanceof UseCase) {
            UseCase uc = (UseCase) d;
            return uc.getName();
        } else if (d instanceof FunctionalRequirement) {
            FunctionalRequirement fr = (FunctionalRequirement) d;
            return fr.getName();
        } else if (d instanceof NonFunctionalRequirement) {
            NonFunctionalRequirement nfr = (NonFunctionalRequirement) d;
            return nfr.getName();
        } else {
            return super.toString();
        }
    }
}