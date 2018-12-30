
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;

/**
 *
 * @author simon
 */
public class FinancialYearWrapper implements IDataWrapper<FinancialYear, GUIFinancialYear>
{
    @Override
    public GUIFinancialYear wrapEntity(FinancialYear entity) {
        return new GUIFinancialYear(entity);
    }
}
