/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "BUSINESS_CATEGORY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BusinessCategory.findAll", query = "SELECT b FROM BusinessCategory b"),
    @NamedQuery(name = "BusinessCategory.findByIdBusinessCategory", query = "SELECT b FROM BusinessCategory b WHERE b.idBusinessCategory = :idBusinessCategory"),
    @NamedQuery(name = "BusinessCategory.findByName", query = "SELECT b FROM BusinessCategory b WHERE b.name = :name")})
public class BusinessCategory implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_BUSINESS_CATEGORY")
    private BigDecimal idBusinessCategory;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idBusinessCategory")
    private Collection<FunctionalRequirement> functionalRequirementCollection;

    public BusinessCategory() {
    }

    public BusinessCategory(BigDecimal idBusinessCategory) {
        this.idBusinessCategory = idBusinessCategory;
    }

    public BusinessCategory(BigDecimal idBusinessCategory, String name, String description) {
        this.idBusinessCategory = idBusinessCategory;
        this.name = name;
        this.description = description;
    }

    public BigDecimal getIdBusinessCategory() {
        return idBusinessCategory;
    }

    public void setIdBusinessCategory(BigDecimal idBusinessCategory) {
        this.idBusinessCategory = idBusinessCategory;
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

    @XmlTransient
    public Collection<FunctionalRequirement> getFunctionalRequirementCollection() {
        return functionalRequirementCollection;
    }

    public void setFunctionalRequirementCollection(Collection<FunctionalRequirement> functionalRequirementCollection) {
        this.functionalRequirementCollection = functionalRequirementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBusinessCategory != null ? idBusinessCategory.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BusinessCategory)) {
            return false;
        }
        BusinessCategory other = (BusinessCategory) object;
        if ((this.idBusinessCategory == null && other.idBusinessCategory != null) || (this.idBusinessCategory != null && !this.idBusinessCategory.equals(other.idBusinessCategory))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.BusinessCategory[ idBusinessCategory=" + idBusinessCategory + " ]";
    }
    
}
