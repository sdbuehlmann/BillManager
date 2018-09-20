package ch.buhls.billmanager.model;

import ch.buhls.billmanager.model.converter.ConverterException;
import ch.buhls.billmanager.model.converter.SVGToPDFConverter;
import ch.buhls.billmanager.model.data.TemplateData;
import ch.buhls.billmanager.model.templates.Template;
import ch.buhls.billmanager.model.templates.TemplateLoader;
import ch.buhls.billmanager.model.templates.TemplateWriter;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author simon
 */
public class ModelFascade
{

    private final static String FILE_ENDING = ".hbcm";

    Logger LOG = java.util.logging.Logger.getLogger(ModelFascade.class.getName());

    // services
    private TemplateLoader templateLoader;
    private TemplateWriter writer;
    private SVGToPDFConverter converter;

    public ModelFascade() {
        templateLoader = new TemplateLoader();
        writer = new TemplateWriter();
        converter = new SVGToPDFConverter();
    }
    
    public void createPDF(
            File destinationFile,
            File templateFile,
            File inkscapeDir,
            TemplateData data) throws ModelException {
        try {
            Template template = templateLoader.getTemplate(templateFile);
            writer.fillTemplate(template, data);

            // create temp file for svg
            File svgFile = new File(destinationFile.getParentFile(), "temp.svg");

            templateLoader.storeTemplate(template, svgFile);

            try{
                converter.convert(svgFile, destinationFile, inkscapeDir);
            }catch(ConverterException ex){
                // delete temp
                Files.delete(svgFile.toPath());
                throw ex;
            }

            // delete temp
            Files.delete(svgFile.toPath());
        }
        catch (Exception ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public void openPDF(File pdfFile) throws IOException {
        System.openPDF(pdfFile);
    }
    
    public void printPDFs(List<File> pdfFiles) throws Exception{
        System.printPDF(pdfFiles);
    }

    public Project createProject(File location) throws ModelException {
        /*
    - Project
    -- templates (Dir)
    -- bills (Dir)
    -- temp (Dir)
    -- database
    -- info.hbcm (version etc.)
         */

        if (location == null
                || location.exists()) {
            throw new ModelException("Can not create project.");
        }

        location.mkdir();

        File locationTemplates = new File(location, "templates");
        locationTemplates.mkdir();

        File locationBills = new File(location, "bills");
        locationBills.mkdir();

        File locationTemp = new File(location, "temp");
        locationTemp.mkdir();
        
        return new Project(
                location,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(new File(location, location.getName() + FILE_ENDING)));
    }

    public Project loadProject(File projectFile) throws ModelException {
        if (projectFile == null
                || !projectFile.isFile()) {
            throw new ModelException("Can not load project.");
        }

        //ProjectInfo projectInfo = XMLHandler.INSTANCE.loadProjectInfo(projectFile);

        File projectDir = projectFile.getParentFile();

        File locationTemplates = new File(projectDir, "templates");
        File locationBills = new File(projectDir, "bills");
        File locationTemp = new File(projectDir, "temp");
        
        return new Project(
                projectDir,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(projectFile));
    }
    
    public static String createBillFilename(int number, String ending) {
        String storageName = number + "";
        return storageName + ending;
    }
    public static File createPathToBill(File dir, int number, String ending){
        return new File(dir, ModelFascade.createBillFilename(number, ending));
    }
    public static File createPathToBillPDF(File dir, int number){
        return ModelFascade.createPathToBill(dir, number, ".pdf");
    }
    
}
