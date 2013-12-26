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

    private FunctionalRequirementController functionalRequirementController;

    private NonFunctionalRequirementController nonFunctionalRequirementController;

    private UseCaseController useCaseController;

    private AltranTreeNode root, selectedNode, functionalRequirementTreeNode, nonFunctionalRequirementTreeNode;
    private AltranTreeNode Instalacao, Interface, Operacionais, Politicos, Seguranca, Usabilidade;

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
            root = new AltranTreeNode("root", null);
            root.setTreenode(this);
            functionalRequirementTreeNode = new AltranTreeNode("Functional Requirement", root);
            functionalRequirementTreeNode.setTreenode(this);
            nonFunctionalRequirementTreeNode = new AltranTreeNode("Non-Functional Requirement", root);
            nonFunctionalRequirementTreeNode.setTreenode(this);
            Project projectSelected = (Project) projectBean.getSelected();
            
            FunctionalRequirementFilter funcfilter = new FunctionalRequirementFilter();
            funcfilter.setProjecto(Integer.parseInt(projectSelected.getIdProject().toString()));
            functionalRequirements = functionalService.findFunctionalRequirementByFilter(funcfilter);

            for (FunctionalRequirement functionalRequirement : functionalRequirements) {
                AltranTreeNode aux = new AltranTreeNode(functionalRequirement, functionalRequirementTreeNode);
                aux.setTreenode(this);
                for (UseCase useCase : functionalRequirement.getUseCaseCollection()) {
                    AltranTreeNode UC = new AltranTreeNode(useCase, aux);
                    UC.setTreenode(this);
                }
            }

            nonFunctionalRequirements = projectSelected.getNonFunctionalRequirementCollection();
            RNFunctionalFilter rnffilter = new RNFunctionalFilter();
            rnffilter.setProject(Integer.parseInt(projectSelected.getIdProject().toString()));

            Instalacao = new AltranTreeNode("Instalação", nonFunctionalRequirementTreeNode);
            Instalacao.setTreenode(this);
            Interface = new AltranTreeNode("Interface e Imagem", nonFunctionalRequirementTreeNode);
            Interface.setTreenode(this);
            Operacionais = new AltranTreeNode("Operacionais", nonFunctionalRequirementTreeNode);
            Operacionais.setTreenode(this);
            Politicos = new AltranTreeNode("Politicos", nonFunctionalRequirementTreeNode);
            Politicos.setTreenode(this);
            Seguranca = new AltranTreeNode("Segurança", nonFunctionalRequirementTreeNode);
            Seguranca.setTreenode(this);
            Usabilidade = new AltranTreeNode("Usabilidade", nonFunctionalRequirementTreeNode);
            Usabilidade.setTreenode(this);

            rnffilter.setType(0);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Instalacao);
                NR.setTreenode(this);
            }

            rnffilter.setType(1);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Interface);
                NR.setTreenode(this);
            }

            rnffilter.setType(2);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Operacionais);
                NR.setTreenode(this);
            }

            rnffilter.setType(3);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Politicos);
                NR.setTreenode(this);
            }

            rnffilter.setType(4);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Seguranca);
                NR.setTreenode(this);
            }

            rnffilter.setType(5);
            nonFunctionalRequirementList = rnfService.findRNFByFilter(rnffilter);
            for (NonFunctionalRequirement nonFunctionalRequirement : nonFunctionalRequirementList) {
                AltranTreeNode NR = new AltranTreeNode(nonFunctionalRequirement, Usabilidade);
                NR.setTreenode(this);
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
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/index.xhtml");
        } else if (strEvent.equals(nonFunctionalRequirementTreeNode.toString())) {
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Instalacao.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(0);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Interface.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(1);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Operacionais.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(2);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Politicos.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(3);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Seguranca.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(4);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (strEvent.equals(Usabilidade.toString())) {
            projectBean.setCateg(true);
            projectBean.setIdCategNRF(5);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/index.xhtml");
        } else if (d instanceof UseCase) {
            UseCase uc = (UseCase) d;
            useCaseController = new UseCaseController();
            useCaseController.setSelected(uc);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/useCase/indexByTree.xhtml");
        } else if (d instanceof FunctionalRequirement) {
            FunctionalRequirement fr = (FunctionalRequirement) d;
            functionalRequirementController = new FunctionalRequirementController();
            functionalRequirementController.setSelected(fr);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/functionalRequirement/indexByTree.xhtml");
        } else if (d instanceof NonFunctionalRequirement) {
            NonFunctionalRequirement nfr = (NonFunctionalRequirement) d;
            nonFunctionalRequirementController = new NonFunctionalRequirementController();
            nonFunctionalRequirementController.setSelected(nfr);
            externalContext.redirect(externalContext.getApplicationContextPath() + "/faces/project/nonFunctionalRequirement/indexByTree.xhtml");
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
