/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "ALTRANREQ_ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AltranreqRole.findAll", query = "SELECT a FROM AltranreqRole a"),
    @NamedQuery(name = "AltranreqRole.findByIdRole", query = "SELECT a FROM AltranreqRole a WHERE a.idRole = :idRole"),
    @NamedQuery(name = "AltranreqRole.findByType", query = "SELECT a FROM AltranreqRole a WHERE a.type = :type"),
    @NamedQuery(name = "AltranreqRole.findByDescription", query = "SELECT a FROM AltranreqRole a WHERE a.description = :description")})
public class AltranreqRole implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ROLE")
    private BigDecimal idRole;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "TYPE")
    private String type;
    @Size(max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinTable(name = "ROLE_PRIVILEGE", joinColumns = {
        @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_PRIVILEGE", referencedColumnName = "ID_PRIVILEGE")})
    @ManyToMany
    private Collection<Privilege> privilegeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRole")
    private Collection<ProjectUser> projectUserCollection;

    public AltranreqRole() {
    }

    public AltranreqRole(BigDecimal idRole) {
        this.idRole = idRole;
    }

    public AltranreqRole(BigDecimal idRole, String type) {
        this.idRole = idRole;
        this.type = type;
    }

    public BigDecimal getIdRole() {
        return idRole;
    }

    public void setIdRole(BigDecimal idRole) {
        this.idRole = idRole;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Privilege> getPrivilegeCollection() {
        return privilegeCollection;
    }

    public void setPrivilegeCollection(Collection<Privilege> privilegeCollection) {
        this.privilegeCollection = privilegeCollection;
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
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AltranreqRole)) {
            return false;
        }
        AltranreqRole other = (AltranreqRole) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AltranreqRole[ idRole=" + idRole + " ]";
    }
    
}
