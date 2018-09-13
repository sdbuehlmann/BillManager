
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.FinancialYear;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIFinancialYear extends AGUIData<FinancialYear>
{
    private final StringProperty name;
    private final ObjectProperty<LocalDate> firstDay, lastDay;

    public GUIFinancialYear(FinancialYear year) {
        super(year);
        
        name = new StringAdapterProperty(new IPropertyData<String>(){
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
                return GUIStringCollection.YEAR_NAME;
            }
        });
        firstDay = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>(){
            @Override
            public LocalDate get() {
                return data.getFirstDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set) {
                data.setFirstDay(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.YEAR_FIRST_DAY;
            }
        });
        lastDay = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>(){
            @Override
            public LocalDate get() {
                return data.getLastDay().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set) {
                data.setLastDay(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.YEAR_LAST_DAY;
            }
        });
    }

    public StringProperty getName() {
        return name;
    }

    public ObjectProperty<LocalDate> getFirstDay() {
        return firstDay;
    }

    public ObjectProperty<LocalDate> getLastDay() {
        return lastDay;
    }

    @Override
    public String toString() {
        return name.get();
    }
}
