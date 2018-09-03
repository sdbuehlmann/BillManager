
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUICreateBillsData;
import ch.buhls.billmanager.gui.data.GUIPerson;

/**
 *
 * @author simon
 */
public interface ICreateBillMaskListener
{
    public void create(GUICreateBillsData bill);
    public void removePerson(GUIPerson person);
}
