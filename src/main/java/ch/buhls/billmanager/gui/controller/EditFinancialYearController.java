
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.viewModel.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.FinancialYearMaskBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IDefaultMaskListener;
import ch.buhls.billmanager.persistance.PersistanceException;

/**
 *
 * @author simon
 */
public class EditFinancialYearController extends AFormController<GUIFinancialYear> implements IDefaultMaskListener<GUIFinancialYear>
{
    private final FinancialYearMaskBuilder builder;
    
    public EditFinancialYearController(IGUIFramework framework, DataHandler dataHandler, GUIFinancialYear year) {
        super(framework, dataHandler, framework.getStringCollections().getFinancialYearStringCollection());
        
        builder = new FinancialYearMaskBuilder(dataHandler.editFinancialYear(year), this);
        builder.changeToEditMode();
        
        displayEditMask(builder.getView(), year);
    }

    @Override
    public void save(GUIFinancialYear entity) {
        try {
            if (displayConfirmToStoreDialoque(entity)) {
                this.dataHandler.storeFinancialYear(entity);
                closeMask();
                displayEditedInfoHint(entity);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
