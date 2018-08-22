
package ch.buhls.billmanager.model.svgHandling.elements;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import ch.buhls.billmanager.model.svgHandling.SVGException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author sdb
 */
public abstract class AElement
{
    private static final Logger LOG = Logger.getLogger(AElement.class.getName());
    
    public static final String ATTR_LABEL = "inkscape:label";
    public static final String ATTR_ID = "id";

    private Node node;
    private Node attrID;
    private Node attrLabel;

    private List<AElement> childElements;
    private SVGDocument svgDoc;

    public AElement(Node node, SVGDocument svgDoc)
    {
        NamedNodeMap attr = node.getAttributes();
        attrLabel = attr.getNamedItem(ATTR_LABEL);
        attrID = attr.getNamedItem(ATTR_ID);

        this.node = node;

        this.childElements = new ArrayList<>();
        this.svgDoc = svgDoc;

        LOG.log(Level.FINE, String.format("new element created. NodeName: [%s] ID: [%s] Label: [%s]", node.getNodeName(), this.getID(), this.getLabel()));
    }

    /**
     * Durchsucht direkte Kinderelemente (1 Ebene tiefer) und gibt erstes
     * Element mit entsprechendem Label zurück
     *
     * @param label
     * @return
     * @throws SVGException Kein entsprechendes Element gefunden
     */
    public AElement findByLabel(String label) throws SVGException
    {
        for (AElement element : childElements)
        {
            if (element.getLabel().equals(label))
            {
                return element;
            }
        }
        throw new SVGException(String.format("no element with label [%s] found in element id [%s]", label, this.getID()));
    }

    // getter & setter
    public Node getNode()
    {
        return node;
    }

    public void setNode(Node node)
    {
        this.node = node;
    }

    public List<AElement> getElements()
    {
        return childElements;
    }

    public void setID(String id)
    {
        this.attrID.setTextContent(id);
    }

    public String getID()
    {
        return this.attrID.getTextContent();
    }

    public void setLabel(String label)
    {
        this.attrLabel.setTextContent(label);
    }

    public String getLabel()
    {
        if (this.attrLabel != null)
        {
            return this.attrLabel.getTextContent();
        }
        return "";
    }

    public String getTextContent()
    {
        return this.node.getTextContent();
    }

    public void setTextContent(String text)
    {
        this.node.setTextContent(text);
    }

    public SVGDocument getSvgDocument()
    {
        return svgDoc;
    }

}
