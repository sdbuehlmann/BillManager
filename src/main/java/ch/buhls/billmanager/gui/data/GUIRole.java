
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Role;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIRole extends AGUIData<Role>
{
    private final StringProperty name;
    
    private final BooleanProperty marked;

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
        
        marked = new SimpleBooleanProperty(false);
    }

    public StringProperty getName() {
        return name;
    }

    public BooleanProperty getMarked() {
        return marked;
    }
}
