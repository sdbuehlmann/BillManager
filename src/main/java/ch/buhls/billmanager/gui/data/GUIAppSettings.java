
package ch.buhls.billmanager.gui.data;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIAppSettings
{
    private final StringProperty inkscapePathProperty;
    private final BooleanProperty showDBInfosProperty;

    public GUIAppSettings() {
        inkscapePathProperty = new SimpleStringProperty();
        showDBInfosProperty = new SimpleBooleanProperty();
    }

    public StringProperty getInkscapePathProperty() {
        return inkscapePathProperty;
    }

    public BooleanProperty getShowDBInfosProperty() {
        return showDBInfosProperty;
    }
}
