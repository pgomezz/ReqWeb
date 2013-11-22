/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.altran.altranreq.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "PROJECT_USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProjectUser.findAll", query = "SELECT p FROM ProjectUser p"),
    @NamedQuery(name = "ProjectUser.findByIdUser", query = "SELECT p FROM ProjectUser p WHERE p.projectUserPK.idUser = :idUser"),
    @NamedQuery(name = "ProjectUser.findByIdProject", query = "SELECT p FROM ProjectUser p WHERE p.projectUserPK.idProject = :idProject"),
    @NamedQuery(name = "ProjectUser.findByIdProjIdUser", query = "SELECT p FROM ProjectUser p WHERE p.projectUserPK.idProject = :idProject AND p.projectUserPK.idUser = :idUser"),
    @NamedQuery(name = "ProjectUser.findRoleIdByIdProjIdUser", query = "SELECT ID_ROLE FROM ( SELECT p FROM ProjectUser p WHERE p.projectUserPK.idProject = :idProject AND p.projectUserPK.idUser = :idUser")})
public class ProjectUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjectUserPK projectUserPK;
    @JoinColumn(name = "ID_PROJECT", referencedColumnName = "ID_PROJECT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Project project;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private AltranreqUser altranreqUser;
    @JoinColumn(name = "ID_ROLE", referencedColumnName = "ID_ROLE")
    @ManyToOne(optional = false)
    private AltranreqRole idRole;

    public ProjectUser() {
    }

    public ProjectUser(ProjectUserPK projectUserPK) {
        this.projectUserPK = projectUserPK;
    }

    public ProjectUser(BigDecimal idUser, BigDecimal idProject) {
        this.projectUserPK = new ProjectUserPK(idUser, idProject);
    }

    public ProjectUserPK getProjectUserPK() {
        return projectUserPK;
    }

    public void setProjectUserPK(ProjectUserPK projectUserPK) {
        this.projectUserPK = projectUserPK;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AltranreqUser getAltranreqUser() {
        return altranreqUser;
    }

    public void setAltranreqUser(AltranreqUser altranreqUser) {
        this.altranreqUser = altranreqUser;
    }

    public AltranreqRole getIdRole() {
        return idRole;
    }

    public void setIdRole(AltranreqRole idRole) {
        this.idRole = idRole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectUserPK != null ? projectUserPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectUser)) {
            return false;
        }
        ProjectUser other = (ProjectUser) object;
        if ((this.projectUserPK == null && other.projectUserPK != null) || (this.projectUserPK != null && !this.projectUserPK.equals(other.projectUserPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProjectUser[ projectUserPK=" + projectUserPK + " ]";
    }
    
}
