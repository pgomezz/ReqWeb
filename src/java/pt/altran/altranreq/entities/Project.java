/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PROJECT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p"),
    @NamedQuery(name = "Project.findByIdProject", query = "SELECT p FROM Project p WHERE p.idProject = :idProject"),
    @NamedQuery(name = "Project.findByName", query = "SELECT p FROM Project p WHERE p.name = :name"),
    @NamedQuery(name = "Project.findByBeginDate", query = "SELECT p FROM Project p WHERE p.beginDate = :beginDate"),
    @NamedQuery(name = "Project.findByEndDate", query = "SELECT p FROM Project p WHERE p.endDate = :endDate"),
    @NamedQuery(name = "Project.findByProjectState", query = "SELECT p FROM Project p WHERE p.projectState = :projectState"),
    @NamedQuery(name = "Project.findByIdProjectManager", query = "SELECT p FROM Project p WHERE p.idProjectManager = :idProjectManager"),
    @NamedQuery(name = "Project.findByTerminology", query = "SELECT p FROM Project p WHERE p.terminology = :terminology")})
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_PROJECT")
    
    private BigDecimal idProject;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "BEGIN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date beginDate;
    @Column(name = "END_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PROJECT_STATE")
    private BigInteger projectState;
    @Column(name = "ID_PROJECT_MANAGER")
    private BigInteger idProjectManager;
    @Size(max = 50)
    @Column(name = "TERMINOLOGY")
    private String terminology;
    @JoinColumn(name = "ID_ORGANIZATION", referencedColumnName = "ID_ORGANIZATION")
    @ManyToOne(optional = false)
    private Organization idOrganization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<Document> documentCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<FunctionalRequirement> functionalRequirementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idProject")
    private Collection<ScopeDefinitionInteraction> scopeDefinitionInteractionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
    private Collection<ProjectUser> projectUserCollection;

    public Project() {
    }

    public Project(BigDecimal idProject) {
        this.idProject = idProject;
    }

    public Project(BigDecimal idProject, String name, String description, BigInteger projectState) {
        this.idProject = idProject;
        this.name = name;
        this.description = description;
        this.projectState = projectState;
    }

    public BigDecimal getIdProject() {
        return idProject;
    }

    public void setIdProject(BigDecimal idProject) {
        this.idProject = idProject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BigInteger getProjectState() {
        return projectState;
    }

    public void setProjectState(BigInteger projectState) {
        this.projectState = projectState;
    }

    
    public BigInteger getIdProjectManager() {
        return idProjectManager;
    }

    public void setIdProjectManager(BigInteger idProjectManager) {
        this.idProjectManager = idProjectManager;
    }
    
    public String getTerminology() {
        return terminology;
    }

    public void setTerminology(String terminology) {
        this.terminology = terminology;
    }

    public Organization getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(Organization idOrganization) {
        this.idOrganization = idOrganization;
    }

    @XmlTransient
    public Collection<Document> getDocumentCollection() {
        return documentCollection;
    }

    public void setDocumentCollection(Collection<Document> documentCollection) {
        this.documentCollection = documentCollection;
    }

    @XmlTransient
    public Collection<NonFunctionalRequirement> getNonFunctionalRequirementCollection() {
        return nonFunctionalRequirementCollection;
    }

    public void setNonFunctionalRequirementCollection(Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection) {
        this.nonFunctionalRequirementCollection = nonFunctionalRequirementCollection;
    }

    @XmlTransient
    public Collection<FunctionalRequirement> getFunctionalRequirementCollection() {
        return functionalRequirementCollection;
    }

    public void setFunctionalRequirementCollection(Collection<FunctionalRequirement> functionalRequirementCollection) {
        this.functionalRequirementCollection = functionalRequirementCollection;
    }

    @XmlTransient
    public Collection<ScopeDefinitionInteraction> getScopeDefinitionInteractionCollection() {
        return scopeDefinitionInteractionCollection;
    }

    public void setScopeDefinitionInteractionCollection(Collection<ScopeDefinitionInteraction> scopeDefinitionInteractionCollection) {
        this.scopeDefinitionInteractionCollection = scopeDefinitionInteractionCollection;
    }

    @XmlTransient
    public Collection<ProjectUser> getProjectUserCollection() {
        return projectUserCollection;
    }

    public void setProjectUserCollection(Collection<ProjectUser> projectUserCollection) {
        this.projectUserCollection = projectUserCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Project)) {
            return false;
        }
        Project other = (Project) object;
        if ((this.idProject == null && other.idProject != null) || (this.idProject != null && !this.idProject.equals(other.idProject))) {
            return false;
        }
        return true;
    }
    

    @Override
    public String toString() {
        return "entities.Project[ idProject=" + idProject + " ]";
    }
    
}
