package ch.buhls.billmanager.model.templates.types;

import ch.buhls.billmanager.model.svgHandling.SVGException;
import ch.buhls.billmanager.model.svgHandling.elements.AElement;

/**
 *
 * @author sdb
 */
public class TwoLinePosition
{
    // labels
    public final static String POSITION = "position";
    public final static String LINE1 = "line1";
    public final static String LINE2 = "line2";
    public final static String NUMBER = "menge";
    public final static String PRIZE = "preis";
    public final static String SUBTOTAL = "gesammt";

    public final SingleLineText position;
    public final SingleLineText line1;
    public final SingleLineText line2;
    public final SingleLineText prize;
    public final SingleLineText number;
    public final SingleLineText subtotal;

    private String label;

    public TwoLinePosition(
            String label,
            AElement element) throws SVGException
    {

        this.label = label;

        position = new SingleLineText(POSITION, element.findByLabel(POSITION));
        line1 = new SingleLineText(LINE1, element.findByLabel(LINE1));
        line2 = new SingleLineText(LINE2, element.findByLabel(LINE2));
        prize = new SingleLineText(PRIZE, element.findByLabel(PRIZE));
        number = new SingleLineText(NUMBER, element.findByLabel(NUMBER));
        subtotal = new SingleLineText(SUBTOTAL, element.findByLabel(SUBTOTAL));
    }

    public String getLabel()
    {
        return label;
    }

}
