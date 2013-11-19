/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "ALTERNATIVE_FLOWS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AlternativeFlows.findAll", query = "SELECT a FROM AlternativeFlows a"),
    @NamedQuery(name = "AlternativeFlows.findByIdAlternativeFlows", query = "SELECT a FROM AlternativeFlows a WHERE a.idAlternativeFlows = :idAlternativeFlows")})
public class AlternativeFlows implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ALTERNATIVE_FLOWS")
    private BigDecimal idAlternativeFlows;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "DESCRIPTION")
    private String description;
    @JoinColumn(name = "ID_USE_CASE", referencedColumnName = "ID_USE_CASE")
    @ManyToOne(optional = false)
    private UseCase idUseCase;

    public AlternativeFlows() {
    }

    public AlternativeFlows(BigDecimal idAlternativeFlows) {
        this.idAlternativeFlows = idAlternativeFlows;
    }

    public AlternativeFlows(BigDecimal idAlternativeFlows, String description) {
        this.idAlternativeFlows = idAlternativeFlows;
        this.description = description;
    }

    public BigDecimal getIdAlternativeFlows() {
        return idAlternativeFlows;
    }

    public void setIdAlternativeFlows(BigDecimal idAlternativeFlows) {
        this.idAlternativeFlows = idAlternativeFlows;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UseCase getIdUseCase() {
        return idUseCase;
    }

    public void setIdUseCase(UseCase idUseCase) {
        this.idUseCase = idUseCase;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlternativeFlows != null ? idAlternativeFlows.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AlternativeFlows)) {
            return false;
        }
        AlternativeFlows other = (AlternativeFlows) object;
        if ((this.idAlternativeFlows == null && other.idAlternativeFlows != null) || (this.idAlternativeFlows != null && !this.idAlternativeFlows.equals(other.idAlternativeFlows))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AlternativeFlows[ idAlternativeFlows=" + idAlternativeFlows + " ]";
    }
    
}
