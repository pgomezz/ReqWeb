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
@Table(name = "USE_CASE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UseCase.findAll", query = "SELECT u FROM UseCase u"),
    @NamedQuery(name = "UseCase.findByIdUseCase", query = "SELECT u FROM UseCase u WHERE u.idUseCase = :idUseCase"),
    @NamedQuery(name = "UseCase.findByName", query = "SELECT u FROM UseCase u WHERE u.name = :name"),
    @NamedQuery(name = "UseCase.findByOrdernumber", query = "SELECT u FROM UseCase u WHERE u.ordernumber = :ordernumber")})
public class UseCase implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_USE_CASE")
    private BigDecimal idUseCase;
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
    @Column(name = "AVALIATION_CRITERIA")
    private String avaliationCriteria;
    @Lob
    @Column(name = "PRE_CONDITION")
    private String preCondition;
    @Lob
    @Column(name = "POS_CONDITION")
    private String posCondition;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "MAIN_SCENARIO")
    private String mainScenario;
    @Column(name = "ORDERNUMBER")
    private BigInteger ordernumber;
    @JoinTable(name = "USE_CASE_DEPENDENCE", joinColumns = {
        @JoinColumn(name = "ID_USE_CASE_1", referencedColumnName = "ID_USE_CASE")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USE_CASE_2", referencedColumnName = "ID_USE_CASE")})
    @ManyToMany
    private Collection<UseCase> useCaseCollection;
    @ManyToMany(mappedBy = "useCaseCollection")
    private Collection<UseCase> useCaseCollection1;
    @ManyToMany(mappedBy = "useCaseCollection")
    private Collection<Actor> actorCollection;
    @JoinColumn(name = "ID_FUNCTIONAL_REQUIREMENT", referencedColumnName = "ID_FUNCTIONAL_REQUIREMENT")
    @ManyToOne(optional = false)
    private FunctionalRequirement idFunctionalRequirement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUseCase")
    private Collection<AlternativeFlows> alternativeFlowsCollection;

    public UseCase() {
    }

    public UseCase(BigDecimal idUseCase) {
        this.idUseCase = idUseCase;
    }

    public UseCase(BigDecimal idUseCase, String name, String description, String avaliationCriteria, String mainScenario) {
        this.idUseCase = idUseCase;
        this.name = name;
        this.description = description;
        this.avaliationCriteria = avaliationCriteria;
        this.mainScenario = mainScenario;
    }

    public BigDecimal getIdUseCase() {
        return idUseCase;
    }

    public void setIdUseCase(BigDecimal idUseCase) {
        this.idUseCase = idUseCase;
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

    public String getAvaliationCriteria() {
        return avaliationCriteria;
    }

    public void setAvaliationCriteria(String avaliationCriteria) {
        this.avaliationCriteria = avaliationCriteria;
    }

    public String getPreCondition() {
        return preCondition;
    }

    public void setPreCondition(String preCondition) {
        this.preCondition = preCondition;
    }

    public String getPosCondition() {
        return posCondition;
    }

    public void setPosCondition(String posCondition) {
        this.posCondition = posCondition;
    }

    public String getMainScenario() {
        return mainScenario;
    }

    public void setMainScenario(String mainScenario) {
        this.mainScenario = mainScenario;
    }

    public BigInteger getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(BigInteger ordernumber) {
        this.ordernumber = ordernumber;
    }

    @XmlTransient
    public Collection<UseCase> getUseCaseCollection() {
        return useCaseCollection;
    }

    public void setUseCaseCollection(Collection<UseCase> useCaseCollection) {
        this.useCaseCollection = useCaseCollection;
    }

    @XmlTransient
    public Collection<UseCase> getUseCaseCollection1() {
        return useCaseCollection1;
    }

    public void setUseCaseCollection1(Collection<UseCase> useCaseCollection1) {
        this.useCaseCollection1 = useCaseCollection1;
    }

    @XmlTransient
    public Collection<Actor> getActorCollection() {
        return actorCollection;
    }

    public void setActorCollection(Collection<Actor> actorCollection) {
        this.actorCollection = actorCollection;
    }

    public FunctionalRequirement getIdFunctionalRequirement() {
        return idFunctionalRequirement;
    }

    public void setIdFunctionalRequirement(FunctionalRequirement idFunctionalRequirement) {
        this.idFunctionalRequirement = idFunctionalRequirement;
    }

    @XmlTransient
    public Collection<AlternativeFlows> getAlternativeFlowsCollection() {
        return alternativeFlowsCollection;
    }

    public void setAlternativeFlowsCollection(Collection<AlternativeFlows> alternativeFlowsCollection) {
        this.alternativeFlowsCollection = alternativeFlowsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUseCase != null ? idUseCase.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UseCase)) {
            return false;
        }
        UseCase other = (UseCase) object;
        if ((this.idUseCase == null && other.idUseCase != null) || (this.idUseCase != null && !this.idUseCase.equals(other.idUseCase))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UseCase[ idUseCase=" + idUseCase + " ]";
    }
    
}
