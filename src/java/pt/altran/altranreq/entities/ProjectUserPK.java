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
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class ProjectUserPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USER")
    private BigDecimal idUser;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PROJECT")
    private BigDecimal idProject;

    public ProjectUserPK() {
    }

    public ProjectUserPK(BigDecimal idUser, BigDecimal idProject) {
        this.idUser = idUser;
        this.idProject = idProject;
    }

    public BigDecimal getIdUser() {
        return idUser;
    }

    public void setIdUser(BigDecimal idUser) {
        this.idUser = idUser;
    }

    public BigDecimal getIdProject() {
        return idProject;
    }

    public void setIdProject(BigDecimal idProject) {
        this.idProject = idProject;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        hash += (idProject != null ? idProject.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectUserPK)) {
            return false;
        }
        ProjectUserPK other = (ProjectUserPK) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        if ((this.idProject == null && other.idProject != null) || (this.idProject != null && !this.idProject.equals(other.idProject))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "db.ProjectUserPK[ idUser=" + idUser + ", idProject=" + idProject + " ]";
    }
    
}