
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIBill.GUIBillStatus;
import java.time.LocalDate;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public interface IListBillsBuilderListener
{
    public void contextMenuOpened(ObservableList<GUIBill> selected);
    
    // edit
    public void edit(GUIBill selected);
    public void changeStateToPaid(GUIBill selected, LocalDate date);    
    
    // pdf handling
    public void showPDF(GUIBill selected);
    public void printPDFs(List<GUIBill> bills);
    
    // filter
    public void filterByStatus(GUIBillStatus status);
    public void showOnlyBillsFromRoleMembers();

    // export
    void exportSelected(List<GUIBill> bills);
}
