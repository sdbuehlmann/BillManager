package ch.buhls.billmanager.model.svgHandling.elements;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import org.w3c.dom.Node;

/**
 *
 * @author sdb
 */
public class TspanElement extends AElement
{
    public static final String TAG = "tspan";
    
    public TspanElement(Node node, SVGDocument svgDoc)
    {
        super(node, svgDoc);
    }
    
}
