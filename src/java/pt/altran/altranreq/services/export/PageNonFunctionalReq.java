package pt.altran.altranreq.services.export;

import java.math.BigInteger;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import pt.altran.altranreq.entities.NonFunctionalRequirement;

// Create Non Functional Requirement 
public class PageNonFunctionalReq {

    // Create sub Chapter usability
    public static void createUsubilidade(XWPFDocument document, List<NonFunctionalRequirement> aux) {
        XWPFParagraph paragraph0 = document.createParagraph();
        paragraph0.setPageBreak(true);

        JavaPoi.setTitleStyle(document.createParagraph(), "Requisitos Não Funcionais");
        
        String textRun = "Os requisitos não funcionais são requisitos que declaram restrições, ou atributos de qualidade para um software e/ou para o processo de desenvolvimento do sistema. Definem-se nesta secção os requisitos não funcionais que caracterizam a solução a implementar.";
        JavaPoi.setBodyStyle(document.createParagraph(), textRun).addCarriageReturn();
        
        JavaPoi.setSubtitleStyle(document.createParagraph(), "Requisitos de Usabilidade");
        
        textRun = "Os requisitos de usabilidade para a solução baseiam-se em princípios "
                + "de facilidade de utilização, compreensão da informação, e numa "
                + "aprendizagem rápida e intuitiva do seu funcionamento por parte dos "
                + "utilizadores finais. A aplicação fornece mecanismos de feedback das "
                + "operações que o utilizador está a realizar, bem como o estado dessas "
                + "operações. A aplicação deve promover uma boa navegabilidade, tendo como "
                + "base as 10 heurísticas de Jakob Nielsen definidas na Engenharia de "
                + "Usabilidade.";
        JavaPoi.setBodyStyle(document.createParagraph(), textRun).addCarriageReturn();
        
        createAllNonFuncReqTable(document, aux);
    }
    
    // Create sub Chapter Inteface
    public static void createInterface(XWPFDocument document, List<NonFunctionalRequirement> aux) {
                
        JavaPoi.setSubtitleStyle(document.createParagraph(), "Requisitos de Interface e Imagem");
        
        createAllNonFuncReqTable(document, aux);
    }
    
    //4.x Operacionais
    //4.x Instalação
    //4.x Manutenção
    //4.x Segurança -> Ver perfis (Ou seja atores)
    
    //4.x Políticos e Culturais
    
    public static void createAllNonFuncReqTable(XWPFDocument doc, List<NonFunctionalRequirement> listFuctionalRequirement) {
        for (NonFunctionalRequirement functionalRequirement : listFuctionalRequirement) {
            createNonFuncReqTable(doc, functionalRequirement);
        }

    }

    //Detect the type of Non Functional Requirement
    private static String getRNFType(BigInteger rnf) {
        switch (rnf.intValue()) {
            case 0:
                return "I";//RNFType.INSTALACAO;
            case 1:
                return "II";//RNFType.INTERFACE_E_IMAGEM;
            case 2:
                return "O";//RNFType.OPERACIONAIS;
            case 3:
                return "P"; //RNFType.POLITICOS;
            case 4:
                return "S"; //RNFType.SEGURANCA;
            case 5:
                return "U"; //RNFType.USABILIDADE;
        }

        throw new IllegalArgumentException("Is not a supported requirement state");

    }
    
    private static int x = 1;
    
    // Create Table
    private static void createNonFuncReqTable(XWPFDocument doc, NonFunctionalRequirement funcReq) {
        int[] cols = {2943, 6507};

        XWPFTable rf = doc.createTable(10, 2);
        
        List<XWPFTableRow> rows = rf.getRows();
        int rowCt = 0;
        int colCt = 0;

        for (XWPFTableRow row : rows) {

            // get the cells in this row
            List<XWPFTableCell> cells = row.getTableCells();
            for (XWPFTableCell cell : cells) {
                // get a table cell properties element (tcPr)
                CTTcPr tcpr = cell.getCTTc().addNewTcPr();

                // create cell color element
                CTShd ctshd = tcpr.addNewShd();

                ctshd.setColor("auto");
                ctshd.setVal(STShd.CLEAR);

                if (colCt == 0) {
                    ctshd.setFill("5C7F92");
                }

                // get 1st paragraph in cell's paragraph list
                XWPFParagraph para = cell.getParagraphs().get(0);
                para.setStyle("AltranNormal");
                para.setSpacingAfter(120);
                para.setSpacingBefore(120);
                
                
                // create a run to contain the content
                XWPFRun rh = para.createRun();
                //rh.setFontSize(11);
                //rh.setFontFamily("Lucida Sans Unicode");

                if (colCt == 0) {
                    rh.setColor("FFFFFF");
                }

                if (rowCt == 0 && colCt == 0) {
                    //para.setStyle("AltranRNFU");
                    //rh.setText("");
                    //rh.setColor("FFFFFF");
                    //rh.setFontSize(10);
                    //rh.setFontFamily("Lucida Sans Unicode");
                    rh.setText("RNF " + ((x < 9) ? "0" + x : x) + " - " + getRNFType(funcReq.getType()));
                    x++;
                } else if (rowCt == 1 && colCt == 0) {
                    rh.setText("Descrição:");
                } else if (rowCt == 2 && colCt == 0) {
                    rh.setText("Fonte:");
                } else if (rowCt == 3 && colCt == 0) {
                    rh.setText("Fundamento:");
                } else if (rowCt == 4 && colCt == 0) {
                    rh.setText("Critério de avaliação:");
                } else if (rowCt == 5 && colCt == 0) {
                    rh.setText("Dependências:");
                } else if (rowCt == 6 && colCt == 0) {
                    rh.setText("Documentação de suporte:");
                } else if (rowCt == 7 && colCt == 0) {
                    rh.setText("Prioridade cliente:");
                } else if (rowCt == 8 && colCt == 0) {
                    rh.setText("Insatisfação do cliente:");
                } else if (rowCt == 9 && colCt == 0) {
                    rh.setText("Histórico:");
                }

                if (rowCt == 0 && colCt == 1) {// Nome do requisito
                    rh.setText(funcReq.getName());
                    rh.setBold(true);
                } else if (rowCt == 1 && colCt == 1) { // UseCases
                    String testUC = "";
                    rh.setText(testUC);
                } else if (rowCt == 2 && colCt == 1) {// Descrição
                    rh.setText(funcReq.getDescription());
                } else if (rowCt == 3 && colCt == 1) {// Fonte
                    rh.setText(funcReq.getSource());
                } else if (rowCt == 4 && colCt == 1) {// Fundamento
                    rh.setText(funcReq.getReason());
                } else if (rowCt == 5 && colCt == 1) {// Criterio de Avalicao
                    rh.setText(funcReq.getAvaliationCriteria());
                } else if (rowCt == 6 && colCt == 1) {// Prioridade
                    rh.setText(funcReq.getClientPriority().toString());
                } else if (rowCt == 7 && colCt == 1) {// Insatisfação
                    rh.setText(funcReq.getClientInsatisfaction().toString());
                } else if (rowCt == 8 && colCt == 1) {// Historico
                    rh.setText("Histórico");
                }

                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[colCt]));
                colCt++;
            }
            colCt = 0;
            rowCt++;

        }

        doc.createParagraph().createRun().addBreak();

    }

}
