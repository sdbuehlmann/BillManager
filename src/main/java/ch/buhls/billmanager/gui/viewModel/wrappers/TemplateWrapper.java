
package ch.buhls.billmanager.gui.viewModel.wrappers;

import ch.buhls.billmanager.gui.data.GUITemplate;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;

/**
 *
 * @author simon
 */
public class TemplateWrapper implements IDataWrapper<BillTemplate, GUITemplate>
{

    @Override
    public GUITemplate wrapEntity(BillTemplate entity) {
        return new GUITemplate(entity);
    }
    
}
