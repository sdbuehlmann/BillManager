
package ch.buhls.billmanager.model.templates;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import ch.buhls.billmanager.model.svgHandling.SVGException;
import ch.buhls.billmanager.model.svgHandling.SVGLoader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

/**
 *
 * @author sdb
 */
public class TemplateLoader
{
    private static final Logger LOG = Logger.getLogger(TemplateLoader.class.getName());
    private SVGLoader svgLoader;
    private List<Template> loadedTemplates;

    public TemplateLoader()
    {
        svgLoader = new SVGLoader();
        loadedTemplates = new ArrayList<>();
    }
    
    public Template loadTemplate(File fileTemplate) throws ParserConfigurationException, SAXException, IOException, SVGException
    {
        SVGDocument svgDoc = svgLoader.loadSVGDocument(fileTemplate);
        Template template = new Template(svgDoc);
        
        return template;
    }
    
    public void storeTemplate(Template template, File templateFile) throws TransformerException
    {
        svgLoader.storeBillDocument(template.getSvgDoc(), templateFile);
    }
    
    public Template getTemplate(File fileTemplate) throws ParserConfigurationException, SAXException, IOException, SVGException
    {
        LOG.log(Level.FINE, String.format("get template. Template:[%s]", fileTemplate.getAbsoluteFile()));

        for (Template template : loadedTemplates)
        {
            if (fileTemplate.equals(template.getFile()))
            {
                LOG.log(Level.FINE, "template allready loaded.");
                return template;
            }
        }

        LOG.log(Level.INFO, "template not loaded yet. Load new...");
        Template template;
        template = loadTemplate(fileTemplate);
        loadedTemplates.add(template);

        return template;
    }
}
