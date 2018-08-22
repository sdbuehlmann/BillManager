
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
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
public class EditFinancialYearController extends AController implements IDefaultMaskListener<GUIFinancialYear>
{
    private final FinancialYearMaskBuilder builder;
    
    public EditFinancialYearController(IGUIFramework framework, DataHandler dataHandler, GUIFinancialYear year) {
        super(framework, dataHandler, GUIStringCollection.getTitleForEditFinancialYear(year));
        
        builder = new FinancialYearMaskBuilder(dataHandler.editFinancialYear(year), this);
        builder.changeToEditMode();
        
        display(builder.getView(), IGUIFramework.Area.RIGHT);
    }

    @Override
    public void save(GUIFinancialYear entity) {
        try {
            if (framework.confirmToStore()) {
                this.dataHandler.storeFinancialYear(entity);
                tabHandle.close();
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
