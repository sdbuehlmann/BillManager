
package ch.buhls.billmanager.model.svgHandling.elements;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import org.w3c.dom.Node;

/**
 *
 * @author sdb
 */
public class TextElement extends AElement
{
    public final static String TAG = "text";
    
    public TextElement(Node node, SVGDocument svgDoc)
    {
        super(node, svgDoc);
    }
    
}
