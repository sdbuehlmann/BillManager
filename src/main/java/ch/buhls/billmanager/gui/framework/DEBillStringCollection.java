
package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.data.GUIBill;

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
    
}
