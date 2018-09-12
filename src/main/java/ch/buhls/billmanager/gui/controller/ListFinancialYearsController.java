
package ch.buhls.billmanager.gui.controller;

import ch.buhls.billmanager.gui.DataHandler;
import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.view.builder.ListFinancialYearsBuilder;
import ch.buhls.billmanager.gui.view.builder.listener.IListFinancialYearsBuilderListener;

/**
 *
 * @author simon
 */
public class ListFinancialYearsController  extends AController implements IListFinancialYearsBuilderListener
{
    private final ListFinancialYearsBuilder builder;
    
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
    
}
