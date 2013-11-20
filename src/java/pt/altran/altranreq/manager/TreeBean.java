package pt.altran.altranreq.manager;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.NodeCollapseEvent;
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;
import pt.altran.altranreq.services.ProjectService;

@Named(value = "treeBean")
@ViewScoped
public class TreeBean implements Serializable {

    @Inject
    private ProjectService ejbService;

    private TreeNode root;
    
    private TreeNode selectedNode;  

    public TreeBean() {

    }

    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);
        TreeNode FR = new DefaultTreeNode("Functional Requirement", root);
        TreeNode NFR = new DefaultTreeNode("Non-Functional Requirement", root);
        
        Collection<FunctionalRequirement> functionalRequirements = ejbService.find(new BigDecimal(1)).getFunctionalRequirementCollection();
        for (FunctionalRequirement functionalRequirement : functionalRequirements) {
            DefaultTreeNode aux = new DefaultTreeNode(functionalRequirement.getName(), FR);
            for (UseCase useCase:functionalRequirement.getUseCaseCollection()){
                new DefaultTreeNode(useCase.getName(), aux);
            }
        }
        
        Collection<NonFunctionalRequirement> nonFunctionalRequirements = ejbService.find(new BigDecimal(1)).getNonFunctionalRequirementCollection();
        for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirements) {
            new DefaultTreeNode(nonFunctionalRequirement.getName(), NFR);
        }
        
    }

    public TreeNode getRoot() {
        return root;
    }
    
    public TreeNode getSelectedNode() {  
        return selectedNode;  
    }  
  
    public void setSelectedNode(TreeNode selectedNode) {  
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
  
    public void onNodeSelect(NodeSelectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
  
    public void onNodeUnselect(NodeUnselectEvent event) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());  
  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }
}
