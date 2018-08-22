package ch.buhls.billmanager.model.templates;

import ch.buhls.billmanager.model.svgHandling.SVGDocument;
import ch.buhls.billmanager.model.svgHandling.SVGException;
import ch.buhls.billmanager.model.templates.types.MultiLineText;
import ch.buhls.billmanager.model.templates.types.NineCharSum;
import ch.buhls.billmanager.model.templates.types.SingleLineText;
import ch.buhls.billmanager.model.templates.types.TwoLinePosition;
import java.io.File;

/**
 *
 * @author sdb
 */
public class Template
{

    // ids

    public final static String HEADER = "rechnungstitel";
    public final static String SALUTATION = "anrede";
    public final static String ADDRESS = "addresse";
    public final static String DATE = "datum";

    public final static String POS1 = "position1";
    public final static String POS2 = "position2";
    public final static String POS3 = "position3";
    public final static String POS4 = "position4";
    public final static String TOTAL = "total";

    public final static String SUM_LEFT = "sum_left";
    public final static String SUM_RIGHT = "sum_right";
    public final static String PURPOSE = "zahlungszweck";

    public SingleLineText header;
    public SingleLineText salutation;
    public final MultiLineText address;
    public SingleLineText date;

    public final TwoLinePosition[] position;

    public SingleLineText total;

    public final NineCharSum sumLeft;
    public final NineCharSum sumRight;
    public final MultiLineText purpose;
    
    private SVGDocument svgDoc;

    public Template(SVGDocument svgDoc) throws SVGException
    {
        this.svgDoc = svgDoc;
        
        header = new SingleLineText(HEADER, svgDoc.getElementByID(HEADER));
        salutation = new SingleLineText(SALUTATION, svgDoc.getElementByID(SALUTATION));
        address = new MultiLineText(ADDRESS, svgDoc.getElementByID(ADDRESS));
        date = new SingleLineText(DATE, svgDoc.getElementByID(DATE));

        position = new TwoLinePosition[4];
        position[0] = new TwoLinePosition(POS1, svgDoc.getElementByID(POS1));
        position[1] = new TwoLinePosition(POS2, svgDoc.getElementByID(POS2));
        position[2] = new TwoLinePosition(POS3, svgDoc.getElementByID(POS3));
        position[3] = new TwoLinePosition(POS4, svgDoc.getElementByID(POS4));

        total = new SingleLineText(TOTAL, svgDoc.getElementByID(TOTAL));

        sumLeft = new NineCharSum(SUM_LEFT, svgDoc.getElementByID(SUM_LEFT));
        sumRight = new NineCharSum(SUM_RIGHT, svgDoc.getElementByID(SUM_RIGHT));
        purpose = new MultiLineText(PURPOSE, svgDoc.getElementByID(PURPOSE));
    }

    public SVGDocument getSvgDoc()
    {
        return svgDoc;
    }
    
    public File getFile()
    {
        return svgDoc.getFile();
    }
}
