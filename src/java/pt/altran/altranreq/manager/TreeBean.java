package pt.altran.altranreq.manager;

import pt.altran.altranreq.manager.requirement.NonFunctionalRequirementController;
import pt.altran.altranreq.manager.usecase.UseCaseController;
import pt.altran.altranreq.manager.requirement.FunctionalRequirementController;
import java.io.IOException;
import java.io.Serializable;
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
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.UseCase;
import pt.altran.altranreq.manager.util.AltranTreeNode;
import pt.altran.altranreq.manager.util.TypeNonFunctionalEnum;
import pt.altran.altranreq.manager.util.UpdateCurrentTreeNode;
import pt.altran.altranreq.services.FunctionalRequirementFilter;
import pt.altran.altranreq.services.FunctionalRequirementService;
import pt.altran.altranreq.services.ProjectService;
import pt.altran.altranreq.services.ProjectServiceBean;
import pt.altran.altranreq.services.RNFService;
import pt.altran.altranreq.services.RNFunctionalFilter;
import pt.altran.altranreq.services.TreeService;

//in coments:
//FunctionalRequirement should be refered by FR;
//NonFunctionalRequirement should be refered by NFR;
//UseCase should be refered by UC;
@Named(value = "treeBean")
@ViewScoped
public class TreeBean implements Serializable, UpdateCurrentTreeNode {

    @Inject
    private ProjectService projectService;

    @Inject
    private TreeService treeService;

    @Inject
    private RNFService rnfService;

    @Inject
    private FunctionalRequirementService functionalService;

    @Inject
    private ProjectServiceBean projectBean;
    
    @Inject
    private BeanPagina beanPagina;

    private FunctionalRequirementController functionalRequirementController;

    private NonFunctionalRequirementController nonFunctionalRequirementController;

    private UseCaseController useCaseController;

    private AltranTreeNode root, selectedNode, functionalRequirementTreeNode, nonFunctionalRequirementTreeNode;
    private AltranTreeNode aux, aux2;

    private Collection<FunctionalRequirement> functionalRequirements;
    private Collection<NonFunctionalRequirement> nonFunctionalRequirements, nonFunctionalRequirementList;

    private ArrayList<String> str;

    private int id = -1;

    private TreeNode currentTreeNode;

    public String getStr() {
        id++;
        return str.get(id);
    }

    public ArrayList<String> getStrr() {
        return str;
    }

    public TreeBean() {
    }

    @PostConstruct
    public void init() {
        //In this method is created the tree, with all the nodes:
        //FunctionalRequirement and NonFunctionalRequirement Nodes;
        //In FunctionalRequirement all the nodes were dynamic. and if there are UseCases,  
        //they appear like new nodes;
        //In NonFunctionalRequirement, there are 6 category's: Instalacao, Interface, Operacionais, Politicos, 
        //Seguranca, Usabilidade;
        //In each one, all the nodes are dynamic too.
        root = new AltranTreeNode(this, "root", null);
        functionalRequirementTreeNode = new AltranTreeNode(this, "Functional Requirement", root);
        nonFunctionalRequirementTreeNode = new AltranTreeNode(this, "Non-Functional Requirement", root);
        Project projectSelected = (Project) projectBean.getSelected();

        FunctionalRequirementFilter funcfilter = new FunctionalRequirementFilter();
        funcfilter.setProjecto(Integer.parseInt(projectSelected.getIdProject().toString()));
        functionalRequirements = functionalService.findFunctionalRequirementByFilter(funcfilter);
        //verificar se getUseCaseCollection está a funcionar
        for (FunctionalRequirement functionalRequirement : functionalRequirements) {
            aux = new AltranTreeNode(this, functionalRequirement, functionalRequirementTreeNode);
            for (UseCase useCase : functionalRequirement.getUseCaseCollection()) {
                aux2 = new AltranTreeNode(this, useCase, aux);
            }
        }

        nonFunctionalRequirements = projectSelected.getNonFunctionalRequirementCollection();
        RNFunctionalFilter rnffilter = new RNFunctionalFilter();
        rnffilter.setProject(Integer.parseInt(projectSelected.getIdProject().toString()));

        for (TypeNonFunctionalEnum type : TypeNonFunctionalEnum.values()) {
            aux = new AltranTreeNode(this, type, nonFunctionalRequirementTreeNode);
            rnffilter.setType(type.ordinal());
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                aux2 = new AltranTreeNode(this, nonFunctionalRequirement, aux);
            }
        }

    }

    public TreeNode getRoot() {
        currentTreeNode = root;
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
        //Every node has a link;
        //FR and NFR go to the list filtered by Project;
        //In FR nodes and UC nodes, every node go to their own description;
        //In NFR, in each category, every node go also to their own description;
        //In case of clicking in the name of the category, will be redirected to the list of NFR filtered by
        //Project and by category;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected", event.getTreeNode().toString());

        String strEvent = event.getTreeNode().toString();
        Object d = ((AltranTreeNode) event.getTreeNode()).getRealData();
        treeService.setSelected(d);

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        System.out.println(externalContext.getApplicationContextPath());

        if (strEvent.equals(functionalRequirementTreeNode.toString())) {
            beanPagina.setPaginaProj("/project/functionalRequirement/List.xhtml");//V
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");
        } else if (strEvent.equals(nonFunctionalRequirementTreeNode.toString())) {
            beanPagina.setPaginaProj("/project/nonFunctionalRequirement/List.xhtml");//V
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");        
        } else if (d instanceof TypeNonFunctionalEnum){
            TypeNonFunctionalEnum tnfr = (TypeNonFunctionalEnum) d;
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(tnfr.ordinal());
            beanPagina.setPaginaProj("/project/nonFunctionalRequirement/List.xhtml");//V
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");   
        } else if (d instanceof UseCase) {
            UseCase uc = (UseCase) d;
            useCaseController = new UseCaseController();
            useCaseController.setSelected(uc);
            beanPagina.setPaginaProj("/project/useCase/View.xhtml");//V
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");
        } else if (d instanceof FunctionalRequirement) {
            FunctionalRequirement fr = (FunctionalRequirement) d;
            functionalRequirementController = new FunctionalRequirementController();
            functionalRequirementController.setSelected(fr);
            beanPagina.setPaginaProj("/project/functionalRequirement/ViewByTree.xhtml");//V
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");
        } else if (d instanceof NonFunctionalRequirement) {
            NonFunctionalRequirement nfr = (NonFunctionalRequirement) d;
            nonFunctionalRequirementController = new NonFunctionalRequirementController();
            nonFunctionalRequirementController.setSelected(nfr);
            beanPagina.setPaginaProj("/project/nonFunctionalRequirement/View_New.xhtml");
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/index.xhtml");
        } else {
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void onNodeUnselect(NodeUnselectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Unselected", event.getTreeNode().toString());

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public TreeNode getFR() {
        return functionalRequirementTreeNode;
    }

    public void setFR(AltranTreeNode FR) {
        this.functionalRequirementTreeNode = FR;
    }

    public TreeNode getNFR() {
        return nonFunctionalRequirementTreeNode;
    }

    public void setNFR(AltranTreeNode NFR) {
        this.nonFunctionalRequirementTreeNode = NFR;
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
        return currentTreeNode;
    }

    @Override
    public void update(TreeNode treenode) {
        currentTreeNode = treenode;
    }
}
