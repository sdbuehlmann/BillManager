
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.handlers.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.framework.IHintHandle;
import ch.buhls.billmanager.gui.view.builder.ListFinancialYearsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListFinancialYearsBuilderListener;

/**
 *
 * @author simon
 */
public class ListFinancialYearsController  extends AController implements IListFinancialYearsBuilderListener
{
    private final ListFinancialYearsBuilder builder;
    
    private IHintHandle yearMarkedHintHandle;
    
    public ListFinancialYearsController(IGUIFramework framework, DataHandler dataHandler) {
        super(framework, dataHandler, GUIStringCollection.getTitleForListFinancialYears());
        
        builder = new ListFinancialYearsBuilder(this, dataHandler.getFinancialYearsBuffer());
        this.bindBuilder(builder);
        
        this.display(builder.getView(), IGUIFramework.Area.LEFT);
    }

    @Override
    public void create() {
        new CreateFinancialYearController(framework, dataHandler);
    }

    @Override
    public void edit(GUIFinancialYear selected) {
        new EditFinancialYearController(framework, dataHandler, selected);
    }

    @Override
    public void mark(GUIFinancialYear selected) {
        if (selected != null) {
            // remove old hint
            if (yearMarkedHintHandle != null) {
                yearMarkedHintHandle.close();
                yearMarkedHintHandle = null;
            }

            // mark new
            dataHandler.getMarkedYear().set(selected);

            yearMarkedHintHandle = framework.displayMarkedHint(GUIStringCollection.getHintTxt_yearMarked(selected), () -> {
                // close hint
                dataHandler.getMarkedYear().set(null);
                yearMarkedHintHandle.close();
                yearMarkedHintHandle = null;
            });
        }
    }
    
}
