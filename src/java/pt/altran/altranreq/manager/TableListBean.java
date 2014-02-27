package pt.altran.altranreq.manager;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "tableListBean")
@SessionScoped
public class TableListBean  implements Serializable {

    private final static String[] state;  
    private final static String[] stateDescription;  
  
    private final static String[] type;
    private final static String[] typeDescription;
    
    private final static String[] projectState;
    private final static String[] projectStateDescription;
    
    
   static {  
       
        projectState = new String[4];  
        projectState[0] = "0";  
        projectState[1] = "1";  
        projectState[2] = "2";  
        projectState[3] = "3";  
         
        
        projectStateDescription = new String[4];  
        projectStateDescription[0] = "Em curso";  
        projectStateDescription[1] = "Suspenso";  
        projectStateDescription[2] = "Concluído";  
        projectStateDescription[3] = "Em manutenção";  
       
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
    private SelectItem[] projectOptions; 

    public static String[] getState() {
        return state;
    }
    
        public static String[] getProjectState() {
        return projectState;
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
        public SelectItem[] getProjectOptions() {
        return projectOptions;
    }
    
    
 private SelectItem[] createFilterOptions(String[] value, String[] description)  {  
        SelectItem[] options = new SelectItem[value.length + 1];  
  
        options[0] = new SelectItem("", "Selecionar");  
          
        for(int i = 0; i < value.length; i++) {  
            options[i + 1] = new SelectItem(value[i], description[i]);  
        }  
  
        return options;  
    } 
   

    @PostConstruct
    public void init() {
        
        stateOptions = createFilterOptions(state,stateDescription);
        typeOptions = createFilterOptions(type,typeDescription);
        projectOptions = createFilterOptions(projectState, projectStateDescription);
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
