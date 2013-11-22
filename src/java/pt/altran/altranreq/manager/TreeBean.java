package pt.altran.altranreq.manager;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;
import pt.altran.altranreq.manager.util.AltranTreeNode;
import pt.altran.altranreq.manager.util.UpdateCurrentTreeNode;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.TreeService;

@Named(value = "treeBean")
@ViewScoped
public class TreeBean implements Serializable, UpdateCurrentTreeNode {

    @Inject
    private ProjectService ejbService;
    
    @Inject
    private TreeService treeService;

    private FunctionalRequirementController frc;
    
    private NonFunctionalRequirementController nfrc;
    
    private UseCaseController ucc;

    private AltranTreeNode root;

    private AltranTreeNode selectedNode;

    private AltranTreeNode FR;
    private AltranTreeNode NFR;
    private Collection<FunctionalRequirement> functionalRequirements;
    private Collection<NonFunctionalRequirement> nonFunctionalRequirements;

    private ArrayList<String> str;

    private int id = -1;

    private TreeNode current;

    public String getStr() {
        id++;
        return str.get(id);
        //return id;
    }

    public ArrayList<String> getStrr() {
        return str;
    }

    public TreeBean() {
    }

    @PostConstruct
    public void init() {
        root = new AltranTreeNode("root", null);
        root.setTreenode(this);
        FR = new AltranTreeNode("Functional Requirement", root);
        FR.setTreenode(this);
        NFR = new AltranTreeNode("Non-Functional Requirement", root);
        NFR.setTreenode(this);
        //str = new ArrayList<String>();

        //str.add("/ReqWeb/faces/functionalRequirement/index.xhtml");

        //str.add("/ReqWeb/faces/nonFunctionalRequirement/index.xhtml");
        functionalRequirements = ejbService.find(new BigDecimal(1)).getFunctionalRequirementCollection();
        for (FunctionalRequirement functionalRequirement : functionalRequirements) {
            //str.add("/ReqWeb/faces/functionalRequirement/index.xhtml");
        }

        for (FunctionalRequirement functionalRequirement : functionalRequirements) {
            AltranTreeNode aux = new AltranTreeNode(functionalRequirement, FR);
            aux.setTreenode(this);
            //str.add("/ReqWeb/faces/functionalRequirement/ViewByTree.xhtml");
            for (UseCase useCase : functionalRequirement.getUseCaseCollection()) {
                AltranTreeNode UC = new AltranTreeNode(useCase, aux);
                UC.setTreenode(this);
                //str.add("/ReqWeb/faces/useCase/ViewByTree.xhtml");
            }
        }

        //str.add("/ReqWeb/faces/nonFunctionalRequirement/index.xhtml");

        nonFunctionalRequirements = ejbService.find(new BigDecimal(1)).getNonFunctionalRequirementCollection();
        for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirements) {
            AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, NFR);
            NR.setTreenode(this);
            //str.add("/ReqWeb/faces/nonFunctionalRequirement/index.xhtml");
        }

        //id = str.size();
//        for (int i = str.size() - 1; i >= 0; i--) {
//            System.out.println(str.get(i));
//        }

    }

    public TreeNode getRoot() {
        current = root;
        return root;
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(AltranTreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public void onNodeExpand(NodeExpandEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Expanded", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeCollapse(NodeCollapseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Collapsed", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeSelect(NodeSelectEvent event) throws IOException {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());

        Object d = ((AltranTreeNode) event.getTreeNode()).getRealData();
        System.out.println("seleccionou " + d.getClass());

        treeService.setSelected(d);
        
        if (d instanceof UseCase) {
            UseCase uc = (UseCase) d;
            ucc = new UseCaseController();
            ucc.setSelected(uc);
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("/ReqWeb/faces/project/useCase/index.xhtml");
        } else if (d instanceof FunctionalRequirement) {
            FunctionalRequirement fr = (FunctionalRequirement) d;
            //FunctionalRequirementController 
            frc = new FunctionalRequirementController();
            frc.setSelected(fr);
            Object x = treeService.getSelected();
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("/ReqWeb/faces/project/functionalRequirement/index.xhtml"); //?id=" + fr.getIdFunctionalRequirement());

        } else if (d instanceof NonFunctionalRequirement) {
            NonFunctionalRequirement nfr = (NonFunctionalRequirement) d;
            nfrc = new NonFunctionalRequirementController();
            nfrc.setSelected(nfr);
            Object x = treeService.getSelected();
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            externalContext.redirect("/ReqWeb/faces/project/nonFunctionalRequirement/index.xhtml"); 
        } else {

        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public TreeNode getFR() {
        return FR;
    }

    public void setFR(AltranTreeNode FR) {
        this.FR = FR;
    }

    public TreeNode getNFR() {
        return NFR;
    }

    public void setNFR(AltranTreeNode NFR) {
        this.NFR = NFR;
    }

    public Collection<FunctionalRequirement> getFunctionalRequirements() {
        return functionalRequirements;
    }

    public void setFunctionalRequirements(Collection<FunctionalRequirement> functionalRequirements) {
        this.functionalRequirements = functionalRequirements;
    }

    public Collection<NonFunctionalRequirement> getNonFunctionalRequirements() {
        return nonFunctionalRequirements;
    }

    public void setNonFunctionalRequirements(Collection<NonFunctionalRequirement> nonFunctionalRequirements) {
        this.nonFunctionalRequirements = nonFunctionalRequirements;
    }

    public TreeNode getCurrent() {
        return current;
    }

    @Override
    public void update(TreeNode treenode) {
        current = treenode;
    }

}