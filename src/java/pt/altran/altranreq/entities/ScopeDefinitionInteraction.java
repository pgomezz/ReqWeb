/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "SCOPE_DEFINITION_INTERACTION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScopeDefinitionInteraction.findAll", query = "SELECT s FROM ScopeDefinitionInteraction s"),
    @NamedQuery(name = "ScopeDefinitionInteraction.findByIdScopeDefInteraction", query = "SELECT s FROM ScopeDefinitionInteraction s WHERE s.idScopeDefInteraction = :idScopeDefInteraction"),
    @NamedQuery(name = "ScopeDefinitionInteraction.findByType", query = "SELECT s FROM ScopeDefinitionInteraction s WHERE s.type = :type"),
    @NamedQuery(name = "ScopeDefinitionInteraction.findByTitle", query = "SELECT s FROM ScopeDefinitionInteraction s WHERE s.title = :title"),
    @NamedQuery(name = "ScopeDefinitionInteraction.findByInteractionDate", query = "SELECT s FROM ScopeDefinitionInteraction s WHERE s.interactionDate = :interactionDate")})
public class ScopeDefinitionInteraction implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SCOPE_DEF_INTERACTION")
    private BigDecimal idScopeDefInteraction;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPE")
    private BigInteger type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "TITLE")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "INTERACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date interactionDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT")
    @ManyToOne(optional = false)
    private Project idProject;
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID_CLIENT")
    @ManyToOne(optional = false)
    private Client idClient;

    public ScopeDefinitionInteraction() {
    }

    public ScopeDefinitionInteraction(BigDecimal idScopeDefInteraction) {
        this.idScopeDefInteraction = idScopeDefInteraction;
    }

    public ScopeDefinitionInteraction(BigDecimal idScopeDefInteraction, BigInteger type, String title, Date interactionDate, String description) {
        this.idScopeDefInteraction = idScopeDefInteraction;
        this.type = type;
        this.title = title;
        this.interactionDate = interactionDate;
        this.description = description;
    }

    public BigDecimal getIdScopeDefInteraction() {
        return idScopeDefInteraction;
    }

    public void setIdScopeDefInteraction(BigDecimal idScopeDefInteraction) {
        this.idScopeDefInteraction = idScopeDefInteraction;
    }

    public BigInteger getType() {
        return type;
    }

    public void setType(BigInteger type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(Date interactionDate) {
        this.interactionDate = interactionDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Project getIdProject() {
        return idProject;
    }

    public void setIdProject(Project idProject) {
        this.idProject = idProject;
    }

    public Client getIdClient() {
        return idClient;
    }

    public void setIdClient(Client idClient) {
        this.idClient = idClient;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idScopeDefInteraction != null ? idScopeDefInteraction.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScopeDefinitionInteraction)) {
            return false;
        }
        ScopeDefinitionInteraction other = (ScopeDefinitionInteraction) object;
        if ((this.idScopeDefInteraction == null && other.idScopeDefInteraction != null) || (this.idScopeDefInteraction != null && !this.idScopeDefInteraction.equals(other.idScopeDefInteraction))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ScopeDefinitionInteraction[ idScopeDefInteraction=" + idScopeDefInteraction + " ]";
    }
    
}
