
package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIRole;

/**
 *
 * @author simon
 */
public class DEBillStringCollection extends DEDefaultStringCollection<GUIBill>
{

    @Override
    public String getTabTitle_Edit(GUIBill data) {
        return String.format("Rechnung %d bearbeiten",data.getDb_id().get());
    }
    
    public String getTabTitle_Register() {
        return "Rechnung nacherfassen";
    }
    
    public DialoquesStringsTO getConfirmTxt_ImportBill() {
        return new DialoquesStringsTO("Bestätigung erforderlich", "Soll Rechnung importiert werden?");
    }

}
