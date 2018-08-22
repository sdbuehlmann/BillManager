
package ch.buhls.billmanager.model.templates.types;

import ch.buhls.billmanager.model.svgHandling.SVGException;
import ch.buhls.billmanager.model.svgHandling.elements.AElement;


/**
 *
 * @author sdb
 */
public class NineCharSum
{
    public final static String SUM9 = "sum9";
    public final static String SUM8 = "sum8";
    public final static String SUM7 = "sum7";
    public final static String SUM6 = "sum6";
    public final static String SUM5 = "sum5";
    public final static String SUM4 = "sum4";
    public final static String SUM3 = "sum3";
    public final static String SUM2 = "sum2";
    public final static String SUM1 = "sum1";
    public final static String SUM0 = "sum0";
    
    public final SingleChar[] sum = new SingleChar[10];
    
    private String label;

    public NineCharSum(String label, AElement element) throws SVGException
    {
        this.label = label;
        
        sum[0] = new SingleChar(label, element.findByLabel(SUM0));
        sum[1] = new SingleChar(label, element.findByLabel(SUM1));
        sum[2] = new SingleChar(label, element.findByLabel(SUM2));
        sum[3] = new SingleChar(label, element.findByLabel(SUM3));
        sum[4] = new SingleChar(label, element.findByLabel(SUM4));
        sum[5] = new SingleChar(label, element.findByLabel(SUM5));
        sum[6] = new SingleChar(label, element.findByLabel(SUM6));
        sum[7] = new SingleChar(label, element.findByLabel(SUM7));
        sum[8] = new SingleChar(label, element.findByLabel(SUM8));
        sum[9] = new SingleChar(label, element.findByLabel(SUM9));
    }

    public String getLabel()
    {
        return label;
    }
}
