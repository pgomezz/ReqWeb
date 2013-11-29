package pt.altran.altranreq.services;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlRootElement;
import pt.altran.altranreq.entities.RequirementState;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
@XmlRootElement
public class FunctionalRequirementFilter implements Serializable{

    private String name;
    private Integer state;
    private Integer projecto;
    private Integer businessCategory;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getProjecto() {
        return projecto;
    }

    public void setProjecto(Integer projecto) {
        this.projecto = projecto;
    }

    public Integer getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(Integer businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
        public RequirementState convertRequirementState(int arg) {
        switch (arg) {

            case 0:

                return RequirementState.ACTIVO;

            case 1:

                return RequirementState.CANCELADO;

            case 2:

                return RequirementState.CONCLUIDO;

            case 3:

                return RequirementState.VERSIONADO;
        }

        throw new IllegalArgumentException("Is not a supported requirement state");

    }

    public BigInteger convertRequirementStateEnum(RequirementState requirementState) {

        switch (requirementState) {

            case ACTIVO:

               return BigInteger.valueOf(0);
                

            case CANCELADO:

                return BigInteger.valueOf(1);
            case CONCLUIDO:

               return BigInteger.valueOf(2);

            case VERSIONADO:

                return BigInteger.valueOf(3);

        }
        
        throw new IllegalArgumentException("Is not supported requirement state");
    }

}
