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
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.UseCase;

// Create Functional Requirement Table
public class TableFunctionalReq {

    static int x = 1;

    public TableFunctionalReq() {
    }

    public static void createAllReqFuncTable(XWPFDocument doc, List<FunctionalRequirement> listFuctionalRequirement) {
        for (FunctionalRequirement functionalRequirement : listFuctionalRequirement) {
            createReqFuncTable(doc, functionalRequirement);
        }
    }

    protected static void createReqFuncTable(XWPFDocument doc, FunctionalRequirement funcReq) {

        int[] cols = {2943, 6507};

        XWPFTable rf = doc.createTable(9, 2);
        
        // Get a list of the rows in the table
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
                
                rh.setFontFamily("Lucida Sans Unicode");

                if (colCt == 0) {
                    rh.setColor("FFFFFF");
                }

                if (rowCt == 0 && colCt == 0) {
                    rh.setText("RF " + ((x < 9) ? "0" + x : x) + "- F");
                    x++;
                } else if (rowCt == 1 && colCt == 0) {
                    rh.setText("Use Case (se disponível):");
                } else if (rowCt == 2 && colCt == 0) {
                    rh.setText("Descrição:");
                } else if (rowCt == 3 && colCt == 0) {
                    rh.setText("Fonte:");
                } else if (rowCt == 4 && colCt == 0) {
                    rh.setText("Fundamento:");
                } else if (rowCt == 5 && colCt == 0) {
                    rh.setText("Critério de avaliação:");
                } else if (rowCt == 6 && colCt == 0) {
                    rh.setText("Satisfação do cliente:");
                } else if (rowCt == 7 && colCt == 0) {
                    rh.setText("Insatisfação do cliente:");
                } else if (rowCt == 8 && colCt == 0) {
                    rh.setText("Histórico:");
                }

                if (rowCt == 0 && colCt == 1) {// Nome do requisito
                    rh.setText(funcReq.getName());
                    rh.setBold(true);
                } else if (rowCt == 1 && colCt == 1) { // UseCases
                    String testUC = "";
                    int cntUC = 0;
                    for (UseCase uc : funcReq.getUseCaseCollection()) {
                        
                        if (cntUC == 0) {
                            testUC = uc.getName();
                        } else {
                            testUC = testUC + ", " + uc.getName();
                        }
                        cntUC++;
                    }

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
