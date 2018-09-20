
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUIRegisterBillData;

/**
 *
 * @author simon
 */
public interface IRegisterBillMaskListener
{
    public void register(GUIRegisterBillData bill);
    public void selectBillFile();
}
