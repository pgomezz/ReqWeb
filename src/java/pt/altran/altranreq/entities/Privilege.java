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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "PRIVILEGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Privilege.findAll", query = "SELECT p FROM Privilege p"),
    @NamedQuery(name = "Privilege.findByIdPrivilege", query = "SELECT p FROM Privilege p WHERE p.idPrivilege = :idPrivilege"),
    @NamedQuery(name = "Privilege.findByActionName", query = "SELECT p FROM Privilege p WHERE p.actionName = :actionName")})
public class Privilege implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PRIVILEGE")
    private BigDecimal idPrivilege;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "ACTION_NAME")
    private String actionName;
    @ManyToMany(mappedBy = "privilegeCollection")
    private Collection<AltranreqRole> altranreqRoleCollection;

    public Privilege() {
    }

    public Privilege(BigDecimal idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public Privilege(BigDecimal idPrivilege, String actionName) {
        this.idPrivilege = idPrivilege;
        this.actionName = actionName;
    }

    public BigDecimal getIdPrivilege() {
        return idPrivilege;
    }

    public void setIdPrivilege(BigDecimal idPrivilege) {
        this.idPrivilege = idPrivilege;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    @XmlTransient
    public Collection<AltranreqRole> getAltranreqRoleCollection() {
        return altranreqRoleCollection;
    }

    public void setAltranreqRoleCollection(Collection<AltranreqRole> altranreqRoleCollection) {
        this.altranreqRoleCollection = altranreqRoleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrivilege != null ? idPrivilege.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Privilege)) {
            return false;
        }
        Privilege other = (Privilege) object;
        if ((this.idPrivilege == null && other.idPrivilege != null) || (this.idPrivilege != null && !this.idPrivilege.equals(other.idPrivilege))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Privilege[ idPrivilege=" + idPrivilege + " ]";
    }
    
}
