
package pt.altran.altranreq.manager.util;

public enum TypeNonFunctionalEnum {
    INSTALACAO("Instalação"), 
    INTERFACE_E_IMAGEM("Interface e Imagem"),
    OPERACIONAIS("Operacionais"),
    POLITICOS("Politicos"),
    SEGURANCA("Segurança"),
    USABILIDADE("Usabilidade");
    private final String displayName;

    private TypeNonFunctionalEnum(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    
}
