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
import pt.altran.altranreq.services.AbstractService;

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
