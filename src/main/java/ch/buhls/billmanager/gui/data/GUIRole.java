
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
    private final StringProperty name;

    public GUIRole(Role role) {
        super(role);
        
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
                return "Bezeichnung";
            }
        });
    }

    public StringProperty getName() {
        return name;
    }
}
