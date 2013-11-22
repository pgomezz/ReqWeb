package pt.altran.altranreq.services;

import pt.altran.altranreq.entities.Actor;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.entities.UseCase;
import java.util.List;
import pt.altran.altranreq.services.AbstractService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 * @param <T>
 */
public interface ActorService extends AbstractService<Actor> {

    public List<Actor> findActorByProject(int idProject);

    public List<Actor> findActorByUseCase(int idUseCase);
}
