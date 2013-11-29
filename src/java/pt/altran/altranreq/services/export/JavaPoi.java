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
    
    // Injection of the ProjectService for getting the current Project info
    @Inject
    ProjectServiceBean projectService;

    public JavaPoi() {
    }

    //Method for downloading
    public void download(OutputStream out) throws IOException {
        String nomeProjecto = ((Project)projectService.getSelected()).getName();
        InputStream in = new FileInputStream(nomeProjecto +"_SpecReq_v1.0.docx");

        //Prepare.
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

    //Auxiliar method for passing the input to output
    public static void flow(InputStream is, OutputStream os, byte[] buf) throws IOException {
        int numRead;
        while ((numRead = is.read(buf)) >= 0) {
            os.write(buf, 0, numRead);
        }
    }

    //get run with "AltranHeading1" style format
    protected static XWPFRun setTitleStyle(XWPFParagraph titleParagraph, String text) {
        XWPFRun title = titleParagraph.createRun();
        titleParagraph.setStyle("AltranHeading1");
        title.setText(text);
        title.addCarriageReturn();
        return title;
    }

    //get run with "AltranHeading2" style format
    protected static XWPFRun setSubtitleStyle(XWPFParagraph subTitleParagraph, String text) {
        subTitleParagraph.setStyle("AltranHeading2");
        XWPFRun subtitle = subTitleParagraph.createRun();
        subtitle.setText(text);
        subtitle.addCarriageReturn();
        return subtitle;
    }

    //get run with "AltranHeading3" style format
    protected static XWPFRun setSubSubtitleStyle(XWPFParagraph subTitleParagraph, String text) {
        subTitleParagraph.setStyle("AltranHeading3");
        XWPFRun subtitle = subTitleParagraph.createRun();
        subtitle.setText(text);
        subtitle.addCarriageReturn();
        return subtitle;
    }

    //get run with "AltranNormal" style format
    protected static XWPFRun setBodyStyle(XWPFParagraph paragraph, String text) {
        paragraph.setStyle("AltranNormal");
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText(text);
        paragraphRun.addCarriageReturn();
        return paragraphRun;
    }

    //get run with "AltranNormal" style format making bullet like setence
    protected static XWPFRun setBulletStyle(XWPFParagraph paragraph, String text) {
        paragraph.setStyle("AltranNormal");
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("      " + '\u25b6' + "   " + text + ";");
        paragraphRun.addCarriageReturn();
        return paragraphRun;
    }

    // Begin to create all document with all specification
    public void createDocument() {

        Project project = (Project) projectService.getSelected();

        Collection<FunctionalRequirement> aux = project.getFunctionalRequirementCollection();
        //System.out.println(new File(".").getAbsolutePath());
        try {
            //Get the template document saved on the path specified
            XWPFDocument template = new XWPFDocument(new FileInputStream(new File("Template\\AltranEspTemplate.docx")));

            //Document Chapters
            //1. Introduction
            //2. Scope of Project
            //3. Functional Requirement
            //3.x Specification of Functional Requirement
            PageFunctionalRequirements.createPage(template, (List<FunctionalRequirement>) project.getFunctionalRequirementCollection());
            //3.x Specification of  UseCase
            PageUseCase.createPage(template, project.getFunctionalRequirementCollection());

            //4. Non Functional Requirement
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
                    //4.x Interface e Image
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

}
