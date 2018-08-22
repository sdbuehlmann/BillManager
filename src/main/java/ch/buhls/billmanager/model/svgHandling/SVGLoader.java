package ch.buhls.billmanager.model.svgHandling;

import ch.buhls.billmanager.model.svgHandling.elements.AElement;
import ch.buhls.billmanager.model.svgHandling.elements.GElement;
import ch.buhls.billmanager.model.svgHandling.elements.SVGElement;
import ch.buhls.billmanager.model.svgHandling.elements.TextElement;
import ch.buhls.billmanager.model.svgHandling.elements.TspanElement;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author sdb
 */
public class SVGLoader
{
    private static final Logger LOG = Logger.getLogger(SVGLoader.class.getName());
    
    public SVGLoader()
    {

    }

    public SVGDocument loadSVGDocument(File svgFile) throws ParserConfigurationException, SAXException, IOException, SVGException
    {
        LOG.log(Level.INFO, String.format("load SVG document. %s", svgFile.toString()));

        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.parse(svgFile);
        
        SVGDocument svgDocument = new SVGDocument(doc, svgFile);

        SVGElement svgElement = this.findSVGElement(doc, svgDocument);
        this.findChildElements(svgElement);

        return svgDocument;
    }

    private SVGElement findSVGElement(Node node, SVGDocument svgDoc) throws SVGException
    {
        NodeList childNodes = node.getChildNodes();

        for (int cntGroups = 0; cntGroups < childNodes.getLength(); cntGroups++)
        {
            Node activeNode = childNodes.item(cntGroups);

            if (activeNode.getNodeName().equals(SVGElement.TAG))
            {
                return new SVGElement(activeNode, svgDoc);
            }
        }

        throw new SVGException("No SVG Element found in document");
    }

    private void findChildElements(AElement element)
    {
        NodeList childNodes = element.getNode().getChildNodes();

        for (int cntGroups = 0; cntGroups < childNodes.getLength(); cntGroups++)
        {
            Node activeNode = childNodes.item(cntGroups);

            if (activeNode.getNodeType() == Node.ELEMENT_NODE)
            {
                AElement newElement = null;
                
                switch (activeNode.getNodeName())
                {
                    case GElement.TAG:
                        newElement = new GElement(activeNode, element.getSvgDocument());
                        break;

                    case TextElement.TAG:
                        newElement = new TextElement(activeNode, element.getSvgDocument());
                        break;

                    case TspanElement.TAG:
                        newElement = new TspanElement(activeNode, element.getSvgDocument());
                        break;
                }
                
                if(newElement != null)
                {
                    element.getElements().add(newElement);
                    element.getSvgDocument().registerID(newElement.getID(), newElement);
                        
                    this.findChildElements(newElement); // recursion
                }
            }
        }
    }

    public void storeBillDocument(SVGDocument svgDoc, File svgFile) throws TransformerException
    {
        LOG.log(Level.INFO, String.format("store SVG document to %s", svgFile.toString()));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(svgDoc.getDomDocument());
        StreamResult result = new StreamResult(svgFile);
        transformer.transform(source, result);

        LOG.log(Level.INFO, "file stored");
    }

}
