
package ch.buhls.billmanager.model.templates.types;

import ch.buhls.billmanager.model.svgHandling.elements.AElement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class SingleLineText implements IType<String>
{
    private static final Logger LOG = Logger.getLogger(SingleLineText.class.getName());
    
    private AElement element;
    private String label;

    public SingleLineText(String label, AElement element)
    {
        this.label = label;
        this.element = element;
        
        LOG.log(Level.FINE, String.format("single line text with label [%s] connected to element ID [%s] label[%s]", label, element.getID(), element.getLabel()));
    }
    
    public String getTextContent()
    {
        return this.element.getElements().get(0).getTextContent();
    }

    @Override
    public void setContent(String content)
    {
        this.element.getElements().get(0).setTextContent(content);
    }

    @Override
    public String getLabel()
    {
        return label;
    }
}
