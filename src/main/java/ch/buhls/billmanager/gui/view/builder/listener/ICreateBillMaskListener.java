
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUICreateBillData;
import ch.buhls.billmanager.gui.data.GUIPerson;

/**
 *
 * @author simon
 */
public interface ICreateBillMaskListener
{
    public void create(GUICreateBillData bill);
    public void removePerson(GUIPerson person);
}
