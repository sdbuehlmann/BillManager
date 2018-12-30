
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Role;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIRole extends AGUIData<Role>
{
    private final StringAdapterProperty name;

    public GUIRole(Role role) {
        super(role);
        
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
                return "Bezeichnung";
            }
        });
    }

    public StringProperty getName() {
        return name;
    }

    @Override
    public void informBounded() {
        this.name.markInvalid();
    }
}
