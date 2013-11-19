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
import sessionBeans.AbstractService;

/**
 *
 * @author User
 * @param <T>
 */
public interface DocumentService extends AbstractService<Document> {

    public List<Document> findDocumentsByProject(Project project);

    public List<Document> findDocumentsByRequirement(FunctionalRequirement functionalRequirement);

    public List<Document> findDocumentsByProjectAndRequirement(Project project, FunctionalRequirement functionalRequirement);
}
