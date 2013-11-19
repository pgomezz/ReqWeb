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
@Table(name = "ALTRANREQ_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AltranreqUser.findAll", query = "SELECT a FROM AltranreqUser a"),
    @NamedQuery(name = "AltranreqUser.findByIdUser", query = "SELECT a FROM AltranreqUser a WHERE a.idUser = :idUser"),
    @NamedQuery(name = "AltranreqUser.findByName", query = "SELECT a FROM AltranreqUser a WHERE a.name = :name"),
    @NamedQuery(name = "AltranreqUser.findByPassword", query = "SELECT a FROM AltranreqUser a WHERE a.password = :password"),
    @NamedQuery(name = "AltranreqUser.findByUsername", query = "SELECT a FROM AltranreqUser a WHERE a.username = :username"),
    @NamedQuery(name = "AltranreqUser.findByEmail", query = "SELECT a FROM AltranreqUser a WHERE a.email = :email"),
    @NamedQuery(name = "AltranreqUser.findByMobile", query = "SELECT a FROM AltranreqUser a WHERE a.mobile = :mobile"),
    @NamedQuery(name = "AltranreqUser.findByIsAdmin", query = "SELECT a FROM AltranreqUser a WHERE a.isAdmin = :isAdmin")})
public class AltranreqUser implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private BigDecimal idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 88)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IS_ADMIN")
    private char isAdmin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "altranreqUser")
    private Collection<ProjectUser> projectUserCollection;

    public AltranreqUser() {
    }

    public AltranreqUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public AltranreqUser(BigDecimal idUser, String name, String password, String username, String email, String mobile, char isAdmin) {
        this.idUser = idUser;
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.isAdmin = isAdmin;
    }

    public BigDecimal getIdUser() {
        return idUser;
    }

    public void setIdUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public char getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(char isAdmin) {
        this.isAdmin = isAdmin;
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
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AltranreqUser)) {
            return false;
        }
        AltranreqUser other = (AltranreqUser) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AltranreqUser[ idUser=" + idUser + " ]";
    }
    
}
