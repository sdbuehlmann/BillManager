
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.persistance.database.entities.Bill;

/**
 *
 * @author simon
 */
public class BillWrapper implements IDataWrapper<Bill, GUIBill>
{
    @Override
    public GUIBill wrapEntity(Bill bill) {
        GUIBill billCopy = new GUIBill(
                bill,
                new GUITemplate(bill.getTemplate()),
                new GUIFinancialYear(bill.getFinancialYear()),
                new GUIPersonBaseData(bill.getPersonBaseData()));

        return billCopy;
    }
    
}
