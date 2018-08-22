
package ch.buhls.billmanager.model.svgHandling.elements;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import org.w3c.dom.Node;

/**
 *
 * @author sdb
 */
public class SVGElement extends AElement
{
    public static final String TAG = "svg";

    public SVGElement(Node node, SVGDocument svgDocument)
    {
        super(node, svgDocument);
    }
}
