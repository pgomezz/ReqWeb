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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "ORGANIZATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organization.findAll", query = "SELECT o FROM Organization o"),
    @NamedQuery(name = "Organization.findByIdOrganization", query = "SELECT o FROM Organization o WHERE o.idOrganization = :idOrganization"),
    @NamedQuery(name = "Organization.findByName", query = "SELECT o FROM Organization o WHERE o.name = :name"),
    @NamedQuery(name = "Organization.findByBusinessArea", query = "SELECT o FROM Organization o WHERE o.businessArea = :businessArea"),
    @NamedQuery(name = "Organization.findByAddress", query = "SELECT o FROM Organization o WHERE o.address = :address"),
    @NamedQuery(name = "Organization.findByCountry", query = "SELECT o FROM Organization o WHERE o.country = :country")})
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ORGANIZATION")
    private BigDecimal idOrganization;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "BUSINESS_AREA")
    private String businessArea;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "COUNTRY")
    private String country;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "organization")
    private Client client;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrganization")
    private Collection<Project> projectCollection;

    public Organization() {
    }

    public Organization(BigDecimal idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Organization(BigDecimal idOrganization, String name, String businessArea, String address, String country) {
        this.idOrganization = idOrganization;
        this.name = name;
        this.businessArea = businessArea;
        this.address = address;
        this.country = country;
    }

    public BigDecimal getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(BigDecimal idOrganization) {
        this.idOrganization = idOrganization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @XmlTransient
    public Collection<Project> getProjectCollection() {
        return projectCollection;
    }

    public void setProjectCollection(Collection<Project> projectCollection) {
        this.projectCollection = projectCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganization != null ? idOrganization.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.idOrganization == null && other.idOrganization != null) || (this.idOrganization != null && !this.idOrganization.equals(other.idOrganization))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Organization[ idOrganization=" + idOrganization + " ]";
    }
    
}
