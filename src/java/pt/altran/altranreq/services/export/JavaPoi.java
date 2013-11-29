package pt.altran.altranreq.services.export;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;
import org.primefaces.model.StreamedContent;
import pt.altran.altranreq.entities.FunctionalRequirement;
import pt.altran.altranreq.entities.NonFunctionalRequirement;
import pt.altran.altranreq.entities.Project;
import pt.altran.altranreq.services.ProjectServiceBean;

@Named(value = "javaPoi")
@ManagedBean
@RequestScoped
public class JavaPoi {

    @Inject
    ProjectServiceBean projectService;

    private StreamedContent file;

    public JavaPoi() {
    }

    public void download(OutputStream out) throws IOException {
        String nomeProjecto = ((Project)projectService.getSelected()).getName();
        InputStream in = new FileInputStream(nomeProjecto +"_SpecReq_v1.0.docx");

        // Prepare.
        //byte[] pdfData = getItSomehow();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        // Initialize response.
        response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        response.setContentType("application/msword"); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
        response.setHeader("Content-disposition", "attachment; filename=\""+nomeProjecto +"_SpecReq_v1.0.docx\""); // The Save As popup magic is done here. You can give it any filename you want, this only won't work in MSIE, it will use current request URL as filename instead.
        
        OutputStream output = response.getOutputStream();
        flow(in, output, new byte[1024]);
        output.close();

        // Inform JSF to not take the response in hands.
        facesContext.responseComplete();

    }

    public static void flow(InputStream is, OutputStream os, byte[] buf) throws IOException {
        int numRead;
        while ((numRead = is.read(buf)) >= 0) {
            os.write(buf, 0, numRead);
        }
    }

    public StreamedContent getFile() {
        System.out.println("Estao a chamar por mim");
        return file;
    }

    protected static XWPFRun setTitleStyle(XWPFParagraph titleParagraph, String text) {
        XWPFRun title = titleParagraph.createRun();
        titleParagraph.setStyle("AltranHeading1");
        title.setText(text);
        title.addCarriageReturn();
        return title;
    }

    protected static XWPFRun setSubtitleStyle(XWPFParagraph subTitleParagraph, String text) {
        subTitleParagraph.setStyle("AltranHeading2");
        XWPFRun subtitle = subTitleParagraph.createRun();
        subtitle.setText(text);
        subtitle.addCarriageReturn();
        return subtitle;
    }

    protected static XWPFRun setSubSubtitleStyle(XWPFParagraph subTitleParagraph, String text) {
        subTitleParagraph.setStyle("AltranHeading3");
        XWPFRun subtitle = subTitleParagraph.createRun();
        subtitle.setText(text);
        subtitle.addCarriageReturn();
        return subtitle;
    }

    protected static XWPFRun setBodyStyle(XWPFParagraph paragraph, String text) {
        paragraph.setStyle("AltranNormal");
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(text);
        paragraphRun.addCarriageReturn();
        return paragraphRun;
    }

    protected static XWPFRun setBulletStyle(XWPFParagraph paragraph, String text) {
        paragraph.setStyle("AltranNormal");
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("      " + '\u25b6' + "   " + text + ";");
        paragraphRun.addCarriageReturn();
        return paragraphRun;
    }

    //Devera receber o Projecto na totalidade
    public void createDocument() {

        Project project = (Project) projectService.getSelected();

        Collection<FunctionalRequirement> aux = project.getFunctionalRequirementCollection();
        System.out.println(new File(".").getAbsolutePath());
        try {
            //File file = request.getServletContext().getRealPath("\\resources\\AltranEspTemplate.docx");
            XWPFDocument template = new XWPFDocument(new FileInputStream(new File("Template\\AltranEspTemplate.docx")));

            //Estrutura do Documento
            //1. Introdução
            //2. Âmbito do projecto
            //3. Requisitos Funcionais
            //3.x Especificação de Requisitos Funcionais
            PageFunctionalRequirements.createPage(template, (List<FunctionalRequirement>) project.getFunctionalRequirementCollection());
            //3.x Especificação de UseCase
            PageUseCase.createPage(template, project.getFunctionalRequirementCollection());

            //4. Requisitos Não funcionais
            //4.x Usubilidade
            if (project.getNonFunctionalRequirementCollection() != null) {
                boolean Usub = false;
                boolean Interface = false;
                for (NonFunctionalRequirement nonfunctionalRequirement : project.getNonFunctionalRequirementCollection()) {
                    if (BigInteger.valueOf(5) == nonfunctionalRequirement.getType()) {
                        Usub = true;
                    }
                    if (BigInteger.valueOf(1) == nonfunctionalRequirement.getType()) {
                        Interface = true;
                    }
                }

                if (Usub == true) {
                    PageNonFunctionalReq.createUsubilidade(template, (List<NonFunctionalRequirement>) project.getNonFunctionalRequirementCollection());
                }
                if (Interface == true) {
                    //4.x Interface e Imagem
                    PageNonFunctionalReq.createInterface(template, (List<NonFunctionalRequirement>) project.getNonFunctionalRequirementCollection());
                }
            }
            //4.x Operacionais
            //4.x Instalação
            //4.x Manutenção
            //4.x Segurança
            //4.x Políticos e Culturais

            FileOutputStream fos = new FileOutputStream(new File(((Project)projectService.getSelected()).getName() +"_SpecReq_v1.0.docx")); //ProjectName_SpecReq.docx

            template.write(fos);
            
            download(fos);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(JavaPoi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(JavaPoi.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }

    public void teste2() throws FileNotFoundException, IOException {
        FileOutputStream fos = new FileOutputStream(new File("transformed.docx"));
        XWPFDocument doc = new XWPFDocument(new FileInputStream(new File("AltranEspTemplate.docx")));

        for (XWPFParagraph p : doc.getParagraphs()) {
            for (XWPFRun r : p.getRuns()) {
                for (CTText ct : r.getCTR().getTList()) {
                    String str = ct.getStringValue();

                    if (str.contains("${Req.Funcionais}")) {
                        str = str.replace("NAME", "Java Dev");
                        ct.setStringValue(str);
                    }

                }
            }
        }
        doc.write(fos);
    }

}
