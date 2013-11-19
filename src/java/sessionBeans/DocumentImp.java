package sessionBeans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Document;
import entities.FunctionalRequirement;
import entities.Project;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

public class DocumentImp extends AbstractServiceImp<Document> implements DocumentService {

    @PostConstruct
    public void init() {
        setEntityClass(Document.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<Document> findDocumentsByProject(Project project) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
    public List<Document> findDocumentsByRequirement(FunctionalRequirement functionalRequirement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> findDocumentsByProjectAndRequirement(Project project, FunctionalRequirement functionalRequirement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
