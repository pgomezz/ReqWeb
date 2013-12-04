package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "tableListBean")
@ViewScoped
public class TableListBean  implements Serializable {

    private final static String[] state;  
    private final static String[] stateDescription;  
  
    private final static String[] type;
    private final static String[] typeDescription;
    
   static {  
        state = new String[4];  
        state[0] = "0";  
        state[1] = "1";  
        state[2] = "2";  
        state[3] = "3";  
         
        
        stateDescription = new String[4];  
        stateDescription[0] = "Activo";  
        stateDescription[1] = "Versionado";  
        stateDescription[2] = "Cancelado";  
        stateDescription[3] = "Concluido";  
  
        type = new String[6];  
        type[0] = "0";  
        type[1] = "1";  
        type[2] = "2";  
        type[3] = "3";  
        type[4] = "4";  
        type[5] = "5 ";  
        
        
        
        typeDescription = new String[6];  
        typeDescription[0] = "Usabilidade";  
        typeDescription[1] = "Interface";  
        typeDescription[2] = "Operacional";  
        typeDescription[3] = "Instalacao";  
        typeDescription[4] = "Seguranca";  
        typeDescription[5] = "Politicos e culturais"; 
  
    }  
  
      
  
    private SelectItem[] stateOptions; 
    private SelectItem[] typeOptions; 

    public static String[] getState() {
        return state;
    }

    public static String[] getType() {
        return type;
    }

    public SelectItem[] getStateOptions() {
        return stateOptions;
    }

    public SelectItem[] getTypeOptions() {
        return typeOptions;
    }
    
    
    
 private SelectItem[] createFilterOptions(String[] value, String[] description)  {  
        SelectItem[] options = new SelectItem[value.length + 1];  
  
        options[0] = new SelectItem("", "Select");  
          
        for(int i = 0; i < value.length; i++) {  
            options[i + 1] = new SelectItem(value[i], description[i]);  
        }  
  
        return options;  
    } 
   

    @PostConstruct
    public void init() {
        
        stateOptions = createFilterOptions(state,stateDescription);
        typeOptions = createFilterOptions(type,typeDescription);
     
    }
    
    
    public int filterMode(String option){
        
        int intState= 100;
        
        switch(option.toLowerCase()){
            
            case "activo":
            intState=0;
                break;
                
            case "versionado":
            intState=1;
                break;
                
            case "cancelado":
            intState=2;
                break;
            
            case "concluido":
            intState=3;
                break;
                
                
            
        }
        
        
      return intState;  
    }
    

}
