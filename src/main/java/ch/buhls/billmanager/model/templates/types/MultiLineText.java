
package ch.buhls.billmanager.model.templates.types;

import ch.buhls.billmanager.model.svgHandling.elements.AElement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdb
 */
public class MultiLineText implements IType<String[]>
{
    private static final Logger LOG = Logger.getLogger(MultiLineText.class.getName());
    
    private AElement element;
    private String label;

    public MultiLineText(String label, AElement element)
    {
        this.label = label;
        this.element = element;
        
        LOG.log(Level.FINE, String.format("multi line text with label [%s] connected to element ID [%s] label[%s]", label, element.getID(), element.getLabel()));
    }

    @Override
    public void setContent(String[] content)
    {
        for(int cntTextes = 0; cntTextes < content.length; cntTextes++)
        {
            AElement activeElement = element.getElements().get(cntTextes);
            if(activeElement != null)
            {
                activeElement.setTextContent(content[cntTextes]);
            }
        }
    }

    @Override
    public String getLabel()
    {
        return label;
    }
}
