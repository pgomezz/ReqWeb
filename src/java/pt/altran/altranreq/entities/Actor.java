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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
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
@Table(name = "ACTOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
    @NamedQuery(name = "Actor.findByIdActor", query = "SELECT a FROM Actor a WHERE a.idActor = :idActor"),
    @NamedQuery(name = "Actor.findByName", query = "SELECT a FROM Actor a WHERE a.name = :name")})
public class Actor implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ACTOR")
    private BigDecimal idActor;
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
    @JoinTable(name = "ACTOR_USE_CASE", joinColumns = {
        @JoinColumn(name = "ID_ACTOR", referencedColumnName = "ID_ACTOR")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_USE_CASE", referencedColumnName = "ID_USE_CASE")})
    @ManyToMany
    private Collection<UseCase> useCaseCollection;

    public Actor() {
    }

    public Actor(BigDecimal idActor) {
        this.idActor = idActor;
    }

    public Actor(BigDecimal idActor, String name, String description) {
        this.idActor = idActor;
        this.name = name;
        this.description = description;
    }

    public BigDecimal getIdActor() {
        return idActor;
    }

    public void setIdActor(BigDecimal idActor) {
        this.idActor = idActor;
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
    public Collection<UseCase> getUseCaseCollection() {
        return useCaseCollection;
    }

    public void setUseCaseCollection(Collection<UseCase> useCaseCollection) {
        this.useCaseCollection = useCaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idActor != null ? idActor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.idActor == null && other.idActor != null) || (this.idActor != null && !this.idActor.equals(other.idActor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Actor[ idActor=" + idActor + " ]";
    }
    
}
