
package ch.buhls.billmanager.model.svgHandling;

import ch.buhls.billmanager.model.svgHandling.elements.AElement;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

/**
 *
 * @author sdb
 */
public class SVGDocument
{
    private Map<String, AElement> ids;
    
    private Document domDocument;
    private List<AElement> childElements;
    
    private File file;

    public SVGDocument(Document domDocument, File file)
    {
        this.domDocument = domDocument;
        this.childElements = new ArrayList<>();
        this.file = file;
        
        ids = new HashMap<>();
    }
    
    public void registerID(String id, AElement element)
    {
        this.ids.put(id, element);
    }
    
    public AElement getElementByID(String id) throws SVGException
    {
        AElement element = this.ids.get(id);
        if(element != null)
        {
            return element;
        }
        throw new SVGException(String.format("can not found element with id [%s]", id));
    }
    
    // getter
    public Document getDomDocument()
    {
        return domDocument;
    }

    public File getFile()
    {
        return file;
    }

    public List<AElement> getChildElements()
    {
        return childElements;
    }
}
