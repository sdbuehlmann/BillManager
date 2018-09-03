package ch.buhls.billmanager.model;

import ch.buhls.billmanager.model.converter.ConverterException;
import ch.buhls.billmanager.model.converter.SVGToPDFConverter;
import ch.buhls.billmanager.model.data.TemplateData;
import ch.buhls.billmanager.model.svgHandling.SVGException;
import ch.buhls.billmanager.model.templates.Template;
import ch.buhls.billmanager.model.templates.TemplateLoader;
import ch.buhls.billmanager.model.templates.TemplateWriter;
import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.files.ProjectInfo;
import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

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

    public static String createBillFilename(String number, String name, String ending) {
        String storageName = name;
        storageName = storageName.replace(' ', '_');
        storageName = number + "_" + storageName;

        return storageName + ending;
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

        ProjectInfo info = new ProjectInfo();
        info.setVersion("0.2");

        XMLHandler.INSTANCE.storeProjectInfo(new File(location, location.getName() + FILE_ENDING), info);

        return new Project(
                location,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(new File(location, "data.db")),
                info);
    }

    public Project loadProject(File projectFile) throws ModelException {
        if (projectFile == null
                || !projectFile.isFile()) {
            throw new ModelException("Can not create project.");
        }

        ProjectInfo projectInfo = XMLHandler.INSTANCE.loadProjectInfo(projectFile);

        File projectDir = projectFile.getParentFile();

        File locationTemplates = new File(projectDir, "templates");
        File locationBills = new File(projectDir, "bills");
        File locationTemp = new File(projectDir, "temp");

        ProjectInfo info = new ProjectInfo();
        info.setVersion("0.2");

        return new Project(
                projectDir,
                locationTemplates,
                locationBills,
                locationTemp,
                new PersistanceFascade(new File(projectDir, "data.db")),
                info);
    }
}
