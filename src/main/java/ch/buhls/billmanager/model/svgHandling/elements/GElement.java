
package ch.buhls.billmanager.model.svgHandling.elements;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import org.w3c.dom.Node;

/**
 *
 * @author sdb
 */
public class GElement extends AElement
{
    public static final String TAG = "g";
    
    public GElement(Node node, SVGDocument svgDoc)
    {
        super(node, svgDoc);
    }
}
