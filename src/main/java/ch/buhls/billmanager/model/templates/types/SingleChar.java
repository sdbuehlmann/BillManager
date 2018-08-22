
package ch.buhls.billmanager.model.templates.types;

import ch.buhls.billmanager.model.svgHandling.elements.AElement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class SingleChar implements IType<Character>
{
    private static final Logger LOG = Logger.getLogger(SingleChar.class.getName());
    
    private AElement textElement;
    private String label;

    public SingleChar(String label, AElement element)
    {
        this.label = label;
        this.textElement = element;
        
        LOG.log(Level.FINE, String.format("single char with label [%s] connected to element ID [%s] label[%s]", label, element.getID(), element.getLabel()));
    }
    
    @Override
    public void setContent(Character content)
    {
        textElement.getElements().get(0).setTextContent(Character.toString(content));
    }
    
    @Override
    public String getLabel()
    {
        return this.label;
    }
}
