package pt.altran.altranreq.services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pt.altran.altranreq.entities.Document;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
@WebService
@Stateless
public class DocumentImp extends AbstractServiceImp<Document> implements DocumentService {

    @PersistenceContext(unitName="AltranReqPU")
    private EntityManager em;
    
    @PostConstruct
    @WebMethod
    public void init() {
        setEntityClass(Document.class);
    }

    @Override
    @WebMethod
    protected EntityManager getEntityManager() {
        return em;
    }

    
    @Override
    @WebMethod
    public List<Document> findDocumentsByProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    @WebMethod
    public List<Document> findDocumentsByRequirement(FunctionalRequirement functionalRequirement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @WebMethod
    public List<Document> findDocumentsByProjectAndRequirement(Project project, FunctionalRequirement functionalRequirement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
