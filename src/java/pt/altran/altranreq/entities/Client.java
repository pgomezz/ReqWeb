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
import javax.persistence.JoinColumn;
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
@Table(name = "CLIENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Client.findAll", query = "SELECT c FROM Client c"),
    @NamedQuery(name = "Client.findByIdClient", query = "SELECT c FROM Client c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name"),
    @NamedQuery(name = "Client.findByEmail", query = "SELECT c FROM Client c WHERE c.email = :email"),
    @NamedQuery(name = "Client.findByMobile", query = "SELECT c FROM Client c WHERE c.mobile = :mobile"),
    @NamedQuery(name = "Client.findByClientFunction", query = "SELECT c FROM Client c WHERE c.clientFunction = :clientFunction"),
    @NamedQuery(name = "Client.findByOtherContact", query = "SELECT c FROM Client c WHERE c.otherContact = :otherContact"),
    @NamedQuery(name = "Client.findByIdOrganization", query = "SELECT c FROM Client c WHERE c.idOrganization = :idOrganization")})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID_CLIENT")
    private BigDecimal idClient;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "EMAIL")
    private String email;
    @Size(max = 20)
    @Column(name = "MOBILE")
    private String mobile;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 100)
    @Column(name = "CLIENT_FUNCTION")
    private String clientFunction;
    @Size(max = 20)
    @Column(name = "OTHER_CONTACT")
    private String otherContact;
    @Basic(optional = false)
    //@NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ID_ORGANIZATION")
    private String idOrganization;
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_ORGANIZATION", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Organization organization;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClient")
    private Collection<ScopeDefinitionInteraction> scopeDefinitionInteractionCollection;

    public Client() {
    }

    public Client(BigDecimal idClient) {
        this.idClient = idClient;
    }

    public Client(BigDecimal idClient, String name, String email, String mobile, String clientFunction, String idOrganization) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.clientFunction = clientFunction;
        this.idOrganization = idOrganization;
    }

    public BigDecimal getIdClient() {
        return idClient;
    }

    public void setIdClient(BigDecimal idClient) {
        this.idClient = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getClientFunction() {
        return clientFunction;
    }

    public void setClientFunction(String clientFunction) {
        this.clientFunction = clientFunction;
    }

    public String getOtherContact() {
        return otherContact;
    }

    public void setOtherContact(String otherContact) {
        this.otherContact = otherContact;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    @XmlTransient
    public Collection<ScopeDefinitionInteraction> getScopeDefinitionInteractionCollection() {
        return scopeDefinitionInteractionCollection;
    }

    public void setScopeDefinitionInteractionCollection(Collection<ScopeDefinitionInteraction> scopeDefinitionInteractionCollection) {
        this.scopeDefinitionInteractionCollection = scopeDefinitionInteractionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ idClient=" + idClient + " ]";
    }
    
}
