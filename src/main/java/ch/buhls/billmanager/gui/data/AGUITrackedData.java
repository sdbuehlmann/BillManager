
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.ATrackedEntity;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author simon
 * @param <T>
 */
public class AGUITrackedData<T extends ATrackedEntity> extends AGUIData<T>
{
    protected final StringAdapterProperty changeTxt;
    protected final IntegerProperty versionNr;
    protected final ObjectAdapterProperty<LocalDate> dateAdded;
    
    private LocalDate localdate;
    
    public AGUITrackedData(T t) {
        super(t);
        
        changeTxt = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getChangeTxt();
            }

            @Override
            public void set(String set) {
                data.setChangeTxt(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Änderungstext";
            }
        });
        
        versionNr = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getVersionNr();
            }

            @Override
            public void set(Integer set) {
                data.setVersionNr(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Versions Nummer";
            }
        });
        
        localdate = data.getDateAdded().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dateAdded = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>()
        {
            @Override
            public LocalDate get() {
                return localdate;
            }

            @Override
            public void set(LocalDate set) {
                localdate = set;
                data.setDateAdded(Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.TRACKED_ENRITY_DATE;
            }
        });
    }

    public StringAdapterProperty getChangeTxt() {
        return changeTxt;
    }

    public IntegerProperty getVersionNr() {
        return versionNr;
    }

    public ObjectAdapterProperty<LocalDate> getDateAdded() {
        return dateAdded;
    }
}
