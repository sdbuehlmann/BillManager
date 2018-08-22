
package ch.buhls.billmanager.gui.view.builder.listener;

import ch.buhls.billmanager.gui.data.GUIFinancialYear;

/**
 *
 * @author simon
 */
public interface IListFinancialYearsBuilderListener
{
    public void create();
    public void edit(GUIFinancialYear selected);
}
