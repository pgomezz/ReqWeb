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
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "FUNCTIONAL_REQUIREMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FunctionalRequirement.findAll", query = "SELECT f FROM FunctionalRequirement f"),
    @NamedQuery(name = "FunctionalRequirement.findByIdFunctionalRequirement", query = "SELECT f FROM FunctionalRequirement f WHERE f.idFunctionalRequirement = :idFunctionalRequirement"),
    @NamedQuery(name = "FunctionalRequirement.findByName", query = "SELECT f FROM FunctionalRequirement f WHERE f.name = :name"),
    @NamedQuery(name = "FunctionalRequirement.findBySource", query = "SELECT f FROM FunctionalRequirement f WHERE f.source = :source"),
    @NamedQuery(name = "FunctionalRequirement.findByClientPriority", query = "SELECT f FROM FunctionalRequirement f WHERE f.clientPriority = :clientPriority"),
    @NamedQuery(name = "FunctionalRequirement.findByClientInsatisfaction", query = "SELECT f FROM FunctionalRequirement f WHERE f.clientInsatisfaction = :clientInsatisfaction"),
    @NamedQuery(name = "FunctionalRequirement.findByRequirementState", query = "SELECT f FROM FunctionalRequirement f WHERE f.requirementState = :requirementState"),
    @NamedQuery(name = "FunctionalRequirement.findByVersion", query = "SELECT f FROM FunctionalRequirement f WHERE f.version = :version"),
    @NamedQuery(name = "FunctionalRequirement.findByOrdernumber", query = "SELECT f FROM FunctionalRequirement f WHERE f.ordernumber = :ordernumber")})
public class FunctionalRequirement implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_FUNCTIONAL_REQUIREMENT")
    private BigDecimal idFunctionalRequirement;
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
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "REASON")
    private String reason;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "SOURCE")
    private String source;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "AVALIATION_CRITERIA")
    private String avaliationCriteria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_PRIORITY")
    private BigInteger clientPriority;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CLIENT_INSATISFACTION")
    private BigInteger clientInsatisfaction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REQUIREMENT_STATE")
    private BigInteger requirementState;
    @Basic(optional = false)
    @NotNull
    @Column(name = "VERSION")
    private BigInteger version;
    @Column(name = "ORDERNUMBER")
    private BigInteger ordernumber;
    @JoinTable(name = "FUNCTIONAL_ REQ_DEPENDENCE", joinColumns = {
        @JoinColumn(name = "ID_FUNC_REQUIREMENT_1", referencedColumnName = "ID_FUNCTIONAL_REQUIREMENT")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_FUNC_REQUIREMENT_2", referencedColumnName = "ID_FUNCTIONAL_REQUIREMENT")})
    @ManyToMany
    private Collection<FunctionalRequirement> functionalRequirementCollection;
    @ManyToMany(mappedBy = "functionalRequirementCollection")
    private Collection<FunctionalRequirement> functionalRequirementCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idFunctionalRequirement")
    private Collection<UseCase> useCaseCollection;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Project idProject;
    @JoinColumn(name = "ID_BUSINESS_CATEGORY", referencedColumnName = "ID_BUSINESS_CATEGORY")
    @ManyToOne(optional = false)
    private BusinessCategory idBusinessCategory;

    public FunctionalRequirement() {
    }

    public FunctionalRequirement(BigDecimal idFunctionalRequirement) {
        this.idFunctionalRequirement = idFunctionalRequirement;
    }

    public FunctionalRequirement(BigDecimal idFunctionalRequirement, String name, String description, String reason, String source, String avaliationCriteria, BigInteger clientPriority, BigInteger clientInsatisfaction, BigInteger requirementState, BigInteger version) {
        this.idFunctionalRequirement = idFunctionalRequirement;
        this.name = name;
        this.description = description;
        this.reason = reason;
        this.source = source;
        this.avaliationCriteria = avaliationCriteria;
        this.clientPriority = clientPriority;
        this.clientInsatisfaction = clientInsatisfaction;
        this.requirementState = requirementState;
        this.version = version;
    }

    public BigDecimal getIdFunctionalRequirement() {
        return idFunctionalRequirement;
    }

    public void setIdFunctionalRequirement(BigDecimal idFunctionalRequirement) {
        this.idFunctionalRequirement = idFunctionalRequirement;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAvaliationCriteria() {
        return avaliationCriteria;
    }

    public void setAvaliationCriteria(String avaliationCriteria) {
        this.avaliationCriteria = avaliationCriteria;
    }

    public BigInteger getClientPriority() {
        return clientPriority;
    }

    public void setClientPriority(BigInteger clientPriority) {
        this.clientPriority = clientPriority;
    }

    public BigInteger getClientInsatisfaction() {
        return clientInsatisfaction;
    }

    public void setClientInsatisfaction(BigInteger clientInsatisfaction) {
        this.clientInsatisfaction = clientInsatisfaction;
    }

    public BigInteger getRequirementState() {
        return requirementState;
    }

    public static RequirementState convertRequirementState(int arg) {
        switch (arg) {

            case 0:

                return RequirementState.ACTIVO;

            case 1:

                return RequirementState.CANCELADO;

            case 2:

                return RequirementState.CONCLUIDO;

            case 3:

                return RequirementState.VERSIONADO;
        }

        throw new IllegalArgumentException("Is not a supported requirement state");

    }

    public static BigInteger convertRequirementStateEnum(RequirementState requirementState) {

        switch (requirementState) {

            case ACTIVO:

               return BigInteger.valueOf(0);
                

            case CANCELADO:

                return BigInteger.valueOf(1);
            case CONCLUIDO:

               return BigInteger.valueOf(2);

            case VERSIONADO:

                return BigInteger.valueOf(3);

        }
        
        throw new IllegalArgumentException("Is not supported requirement state");
    }

    public void setRequirementState(BigInteger requirementState) {
        this.requirementState = requirementState;
    }

    public BigInteger getVersion() {
        return version;
    }

    public void setVersion(BigInteger version) {
        this.version = version;
    }

    public BigInteger getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(BigInteger ordernumber) {
        this.ordernumber = ordernumber;
    }

    @XmlTransient
    public Collection<FunctionalRequirement> getFunctionalRequirementCollection() {
        return functionalRequirementCollection;
    }

    public void setFunctionalRequirementCollection(Collection<FunctionalRequirement> functionalRequirementCollection) {
        this.functionalRequirementCollection = functionalRequirementCollection;
    }

    @XmlTransient
    public Collection<FunctionalRequirement> getFunctionalRequirementCollection1() {
        return functionalRequirementCollection1;
    }

    public void setFunctionalRequirementCollection1(Collection<FunctionalRequirement> functionalRequirementCollection1) {
        this.functionalRequirementCollection1 = functionalRequirementCollection1;
    }

    @XmlTransient
    public Collection<UseCase> getUseCaseCollection() {
        return useCaseCollection;
    }

    public void setUseCaseCollection(Collection<UseCase> useCaseCollection) {
        this.useCaseCollection = useCaseCollection;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    public BusinessCategory getIdBusinessCategory() {
        return idBusinessCategory;
    }

    public void setIdBusinessCategory(BusinessCategory idBusinessCategory) {
        this.idBusinessCategory = idBusinessCategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFunctionalRequirement != null ? idFunctionalRequirement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FunctionalRequirement)) {
            return false;
        }
        FunctionalRequirement other = (FunctionalRequirement) object;
        if ((this.idFunctionalRequirement == null && other.idFunctionalRequirement != null) || (this.idFunctionalRequirement != null && !this.idFunctionalRequirement.equals(other.idFunctionalRequirement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FunctionalRequirement[ idFunctionalRequirement=" + idFunctionalRequirement + " ]";
    }

}
