
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
public class CreateFinancialYearController extends AFormController<GUIFinancialYear> implements IDefaultMaskListener<GUIFinancialYear>
{
    private final FinancialYearMaskBuilder builder;
    
    public CreateFinancialYearController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, framework.getStringCollections().getFinancialYearStringCollection());
        
        builder = new FinancialYearMaskBuilder(dataHandler.createFinancialYear(), this);
        builder.changeToCreateMode();
        
        displayCreateMask(builder.getView());
    }

    @Override
    public void save(GUIFinancialYear entity) {
        try {
            if (displayConfirmToStoreDialoque(entity)) {
                this.dataHandler.storeFinancialYear(entity);
                tabHandle.close();
                displayCreatedInfoHint(entity);
            }
        }
        catch (PersistanceException ex) {
            framework.showExceptionDialoque(ex);
        }
    }
}
