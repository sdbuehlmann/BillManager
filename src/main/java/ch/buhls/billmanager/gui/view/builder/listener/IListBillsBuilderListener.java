
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUIBill;
import java.util.List;

/**
 *
 * @author simon
 */
public interface IListBillsBuilderListener
{
    public void edit(GUIBill selected);
    
    public void showPDF(GUIBill selected);
    
    public void printPDFs(List<GUIBill> bills);
}
