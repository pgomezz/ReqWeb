package pt.altran.altranreq.services.export;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFNum;
import org.apache.poi.xwpf.usermodel.XWPFNumbering;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTShd;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTcPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STShd;
import pt.altran.altranreq.entities.AlternativeFlows;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;

public class PageUseCase {

    //List of Use Cases attributes
    private static void listUseCase(XWPFParagraph paragraph) {
        JavaPoi.setBulletStyle(paragraph, "Identificador");
        JavaPoi.setBulletStyle(paragraph, "Designação");
        JavaPoi.setBulletStyle(paragraph, "Actor – perfil de utilizador que executa o caso de utilização");
        JavaPoi.setBulletStyle(paragraph, "Descrição indicando em que consiste a acção associada");
        JavaPoi.setBulletStyle(paragraph, "Critério de Avaliação que permite avaliar a sua concretização");
        JavaPoi.setBulletStyle(paragraph, "Pré-Condição que se deve verificar antes da realização do Use Case");
        JavaPoi.setBulletStyle(paragraph, "Cenário principal – comportamento normal e esperado do Use Case (ex.: login aceite)");
        JavaPoi.setBulletStyle(paragraph, "Cenário alternativo N – um comportamento diferente do principal, correspondente a outro resultado possível da execução do Use Case (ex.: login inválido, esqueceu password)");
        JavaPoi.setBulletStyle(paragraph, "Pós-Condição – condição verificada após a realização do Use Case.");

    }
    
    //Create sub chapter UseCase
    public static void createPage(XWPFDocument document, Collection<FunctionalRequirement> listRF) {
        XWPFParagraph paragraph0 = document.createParagraph();
        paragraph0.setPageBreak(true);

        JavaPoi.setSubtitleStyle(document.createParagraph(), "Especificação de Use Cases");

        String textRun = "As funcionalidades previstas para a solução são descritas através de Use Cases (Casos de uso)."
                + "Um Use Case é uma interacção entre o utilizador e o sistema que traduz a execuçãode uma "
                + "determinada operação. Cada Use Case (Caso de uso) caracteriza-se pelos seguintes atributos:";

        JavaPoi.setBodyStyle(document.createParagraph(), textRun).addCarriageReturn();
        listUseCase(document.createParagraph());

        for (FunctionalRequirement functionalRequirement : listRF) {
            listUseCaseByFuncReq(document, functionalRequirement);
        }

    }

    // Division by Functional Requirement of the Use Cases
    private static void listUseCaseByFuncReq(XWPFDocument document, FunctionalRequirement aux) {

        JavaPoi.setSubSubtitleStyle(document.createParagraph(), aux.getName());

        String textRun = "Descrevem-se nesta secção os Use Cases relativos ao requisito referido.";
        JavaPoi.setBodyStyle(document.createParagraph(), textRun);

        listUseCaseTable(document, aux.getUseCaseCollection());

    }

    private static void listUseCaseTable(XWPFDocument doc, Collection<UseCase> listRF) {
        int nTabela = 0;
        for (UseCase useCase : listRF) {
            createUseCaseTabela(doc, useCase, nTabela);
            nTabela++;
        }

    }

    private static XWPFDocument createUseCaseTabela(XWPFDocument doc, UseCase useCase, int nTabela) {
        int[] cols = {2943, 6507};
        int tamTotTabRow = 6 + useCase.getAlternativeFlowsCollection().size() + 1;
        List<AlternativeFlows> lstAF = (List<AlternativeFlows>)useCase.getAlternativeFlowsCollection();

        XWPFTable rf = doc.createTable(tamTotTabRow, 2);

        // Get a list of the rows in the table
        List<XWPFTableRow> rows = rf.getRows();
        int rowCt = 0;
        int colCt = 0;
        int count = 2;

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

                if (rowCt == 0) {
                    ctshd.setFill("C6D9F1");
                }

                // get 1st paragraph in cell's paragraph list
                XWPFParagraph para = cell.getParagraphs().get(0);
                para.setStyle("AltranNormal");
                para.setSpacingAfter(120);
                para.setSpacingBefore(120);

                // create a run to contain the content
                XWPFRun rh = para.createRun();

                rh.setFontSize(9);

                rh.setFontFamily("Lucida Sans Unicode");

                int altFlowsCount = 0;

                if (colCt == 0) {
                    rh.setBold(true);
                    if (rowCt == 0) {
                        rh.setText("USE CASE " + nTabela + ".");
                    } else if (rowCt == 1) {
                        rh.setText("Actor");
                    } else if (rowCt == 2) {
                        rh.setText("Descrição");
                    } else if (rowCt == 3) {
                        rh.setText("Critério de avaliação:");
                    } else if (rowCt == 4) {
                        rh.setText("Pré-condição");
                    } else if (rowCt == 5) {
                        rh.setText("Cenário Principal");
                    } else if (rowCt > 5 && rowCt < 6 + useCase.getAlternativeFlowsCollection().size()) {
                        rh.setText("Cenário Alternativo " + ++altFlowsCount);
                    } else if (rowCt == tamTotTabRow - 1) {
                        rh.setText("Pós-Condição");
                    }
                } else if (colCt == 1) {
                    if (rowCt == 0) {
                        rh.setText(useCase.getName());
                    } else if (rowCt == 1) {
                        //useCase.getActorColection()
                        rh.setText("Actores");
                    } else if (rowCt == 2) {
                        rh.setText(useCase.getDescription());
                    } else if (rowCt == 3) {
                        rh.setText(useCase.getAvaliationCriteria());
                    } else if (rowCt == 4) {
                        rh.setText(useCase.getPreCondition());
                    } else if (colCt == 1 && rowCt == 5) {
                        rh.setText(useCase.getMainScenario());
                    } else if (rowCt > 5 && rowCt < 6 + useCase.getAlternativeFlowsCollection().size()) {
                        rh.setText(lstAF.get(altFlowsCount).getDescription());
                    } else if (rowCt == tamTotTabRow - 1) {
                        rh.setText(useCase.getPosCondition());
                    }
                }

                cell.getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(cols[colCt]));

                colCt++;
            }
            colCt = 0;
            rowCt++;

        }

        doc.createParagraph().createRun().addBreak();
        return doc;
    }
}
