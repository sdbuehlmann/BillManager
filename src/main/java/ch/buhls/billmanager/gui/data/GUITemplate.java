
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.BillTemplate;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUITemplate extends AGUIData<BillTemplate>
{
    private final StringProperty name;
    
    private final IntegerProperty maxNrPositions;

    public GUITemplate(BillTemplate template) {
        super(template);
        
        name = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getName();
            }

            @Override
            public void set(String set) {
                data.setName(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.TEMPLATE_NAME;
            }
        });
        
        maxNrPositions = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getMaxPositions();
            }

            @Override
            public void set(Integer set) {
                data.setMaxPositions(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.TEMPLATE_MAX_POS;
            }
        });
    }

    public StringProperty getName() {
        return name;
    }

    public IntegerProperty getMaxNrPositions() {
        return maxNrPositions;
    }
}
