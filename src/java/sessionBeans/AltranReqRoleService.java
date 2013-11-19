package sessionBeans;


import entities.AltranreqRole;
import java.util.List;
import sessionBeans.AbstractService;

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
public interface AltranReqRoleService extends AbstractService<AltranreqRole> {

    public List<AltranreqRole> findRoleByName(String name);

    public List<AltranreqRole> findRoleByState(Boolean bool);
}
