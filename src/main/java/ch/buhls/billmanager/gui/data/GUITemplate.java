
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
    private final StringAdapterProperty name;
    
    private final IntegerAdapterProperty maxNrPositions;

    public GUITemplate(BillTemplate template) {
        super(template);
        
        name = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return getData().getName();
            }

            @Override
            public void set(String set) {
                getData().setName(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getMaxPositions();
            }

            @Override
            public void set(Integer set) {
                getData().setMaxPositions(set);
            }

            @Override
            public Object getBean() {
                return getData();
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

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public void informBounded() {
        this.name.markInvalid();
        this.maxNrPositions.markInvalid();
    }
}
