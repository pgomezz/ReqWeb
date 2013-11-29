package pt.altran.altranreq.services.export;

import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import pt.altran.altranreq.entities.FunctionalRequirement;

/**
 *
 * @author User
 */
public class PageFunctionalRequirements {
    

    private static void listFunctionalRequirements(XWPFParagraph paragraph, List<FunctionalRequirement> aux) {
        
        for (FunctionalRequirement functionalRequirement : aux) {
            JavaPoi.setBulletStyle(paragraph, functionalRequirement.getName());
        }
        paragraph.createRun().addCarriageReturn();
    }

    public static void createPage(XWPFDocument document, List<FunctionalRequirement> aux) {
        String nomeProjecto = "(Nome do Projecto)";
        
        XWPFParagraph paragraph0 = document.createParagraph();
        paragraph0.setPageBreak(true);

        XWPFParagraph paragraphTitle1 = document.createParagraph();
        JavaPoi.setTitleStyle(paragraphTitle1, "Requisitos Funcionais");
        
        XWPFParagraph paragraphOne = document.createParagraph();      

        String textRun = "Neste capítulo identifica-se o conjunto de requisitos que suportam a actividade "
                + "de gestão e de desenvolvimento da solução. A sua especificação permite à equipa de desenvolvimento "
                + "do projecto perceber claramente o quê e como implementar as funcionalidades solicitadas pelo cliente.";
        JavaPoi.setBodyStyle(paragraphOne, textRun);

        textRun = "Um requisito traduz uma necessidade identificada pelo cliente que deve ser satisfeita pela solução a implementar.";
        JavaPoi.setBodyStyle(paragraphOne, textRun);

        textRun = "Os requisitos funcionais traduzem o conjunto de funcionalidades esperadas da solução, e a sua especificação define a o comportamento desejado da aplicação face à interacção do utilizador.";
        XWPFRun paragraphOneRun3 = JavaPoi.setBodyStyle(paragraphOne, textRun);
        paragraphOneRun3.addCarriageReturn();

        XWPFParagraph paragraphSubtitle1 = document.createParagraph();
        JavaPoi.setSubtitleStyle(paragraphSubtitle1, "Lista de requisitos funcionais");

        XWPFParagraph paragraph2 = document.createParagraph();   
        textRun = "O âmbito da solução é constituído pelos seguintes requisitos funcionais identificados durante o processo de análise de requisitos.";
        JavaPoi.setBodyStyle(paragraph2, textRun);

        listFunctionalRequirements(document.createParagraph(), aux);

        XWPFParagraph paragraphSubtitle2 = document.createParagraph();
        JavaPoi.setSubtitleStyle(paragraphSubtitle2, "Especificação de requisitos funcionais");

        XWPFParagraph paragraph3 = document.createParagraph();
        textRun = "Nesta secção especifica-se detalhadamente cada requisito funcional da solução "+nomeProjecto+" a desenvolver.";
        JavaPoi.setBodyStyle(paragraph3 , textRun);
        
        TableFunctionalReq.createAllReqFuncTable(document, aux);

    }
}
