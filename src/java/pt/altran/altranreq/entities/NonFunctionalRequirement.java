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
@Table(name = "NON_FUNCTIONAL_REQUIREMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NonFunctionalRequirement.findAll", query = "SELECT n FROM NonFunctionalRequirement n"),
    @NamedQuery(name = "NonFunctionalRequirement.findByIdNonFuncRequirement", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.idNonFuncRequirement = :idNonFuncRequirement"),
    @NamedQuery(name = "NonFunctionalRequirement.findByType", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.type = :type"),
    @NamedQuery(name = "NonFunctionalRequirement.findByName", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.name = :name"),
    @NamedQuery(name = "NonFunctionalRequirement.findBySource", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.source = :source"),
    @NamedQuery(name = "NonFunctionalRequirement.findByClientPriority", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.clientPriority = :clientPriority"),
    @NamedQuery(name = "NonFunctionalRequirement.findByClientInsatisfaction", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.clientInsatisfaction = :clientInsatisfaction"),
    @NamedQuery(name = "NonFunctionalRequirement.findByRequirementState", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.requirementState = :requirementState"),
    @NamedQuery(name = "NonFunctionalRequirement.findByVersion", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.version = :version"),
    @NamedQuery(name = "NonFunctionalRequirement.findByOrdernumber", query = "SELECT n FROM NonFunctionalRequirement n WHERE n.ordernumber = :ordernumber")})
public class NonFunctionalRequirement implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_NON_FUNC_REQUIREMENT")
    private BigDecimal idNonFuncRequirement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE")
    private BigInteger type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
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
    @JoinTable(name = "NON_FUNCTIONAL_ REQ_DEPENDENCE", joinColumns = {
        @JoinColumn(name = "ID_NON_FUNCTIONAL_REQ_1", referencedColumnName = "ID_NON_FUNC_REQUIREMENT")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_NON_FUNCTIONAL_REQ_2", referencedColumnName = "ID_NON_FUNC_REQUIREMENT")})
    @ManyToMany
    private Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection;
    @ManyToMany(mappedBy = "nonFunctionalRequirementCollection")
    private Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection1;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Project idProject;

    public NonFunctionalRequirement() {
    }

    public NonFunctionalRequirement(BigDecimal idNonFuncRequirement) {
        this.idNonFuncRequirement = idNonFuncRequirement;
    }

    public NonFunctionalRequirement(BigDecimal idNonFuncRequirement, BigInteger type, String name, String description, String source, String avaliationCriteria, BigInteger clientPriority, BigInteger clientInsatisfaction, BigInteger requirementState, BigInteger version) {
        this.idNonFuncRequirement = idNonFuncRequirement;
        this.type = type;
        this.name = name;
        this.description = description;
        this.source = source;
        this.avaliationCriteria = avaliationCriteria;
        this.clientPriority = clientPriority;
        this.clientInsatisfaction = clientInsatisfaction;
        this.requirementState = requirementState;
        this.version = version;
    }

    public BigDecimal getIdNonFuncRequirement() {
        return idNonFuncRequirement;
    }

    public void setIdNonFuncRequirement(BigDecimal idNonFuncRequirement) {
        this.idNonFuncRequirement = idNonFuncRequirement;
    }

    public BigInteger getType() {
        return type;
    }

    public void setType(BigInteger type) {
        this.type = type;
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
    public Collection<NonFunctionalRequirement> getNonFunctionalRequirementCollection() {
        return nonFunctionalRequirementCollection;
    }

    public void setNonFunctionalRequirementCollection(Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection) {
        this.nonFunctionalRequirementCollection = nonFunctionalRequirementCollection;
    }

    @XmlTransient
    public Collection<NonFunctionalRequirement> getNonFunctionalRequirementCollection1() {
        return nonFunctionalRequirementCollection1;
    }

    public void setNonFunctionalRequirementCollection1(Collection<NonFunctionalRequirement> nonFunctionalRequirementCollection1) {
        this.nonFunctionalRequirementCollection1 = nonFunctionalRequirementCollection1;
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

    public static BigInteger convertRequirementStateEnum(RequirementState type) {

        switch (type) {

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
    
    
    
    
    
    public static RNFType convertRequirementType(int arg) {
        switch (arg) {

            case 0:

                return RNFType.INSTALACAO;

            case 1:

                return RNFType.INTERFACE_E_IMAGEM;

            case 2:

                return RNFType.OPERACIONAIS;

            case 3:

                return RNFType.POLITICOS;
            case 4:

                return RNFType.SEGURANCA;
                
                
                case 5:

                return RNFType.USABILIDADE;
        }

        throw new IllegalArgumentException("Is not a supported requirement state");

    }

    public static BigInteger convertRequirementTypeEnum(RNFType requirementType) {

        switch (requirementType) {

            case INSTALACAO:

               return BigInteger.valueOf(0);
                

            case INTERFACE_E_IMAGEM:

                return BigInteger.valueOf(1);
            case OPERACIONAIS:

               return BigInteger.valueOf(2);

            case POLITICOS:

                return BigInteger.valueOf(3);
                
                case SEGURANCA:

                return BigInteger.valueOf(4);
                    
                    case USABILIDADE:

                return BigInteger.valueOf(5);

        }
        
        throw new IllegalArgumentException("Is not supported requirement state");
    }

    
    
    
    
    
    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNonFuncRequirement != null ? idNonFuncRequirement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NonFunctionalRequirement)) {
            return false;
        }
        NonFunctionalRequirement other = (NonFunctionalRequirement) object;
        if ((this.idNonFuncRequirement == null && other.idNonFuncRequirement != null) || (this.idNonFuncRequirement != null && !this.idNonFuncRequirement.equals(other.idNonFuncRequirement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NonFunctionalRequirement[ idNonFuncRequirement=" + idNonFuncRequirement + " ]";
    }
    
}
