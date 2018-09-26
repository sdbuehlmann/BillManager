
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Person;
import javafx.beans.property.IntegerProperty;

/**
 *
 * @author simon
 */
public class GUIPerson extends AGUIData<Person>
{
    
    private final IntegerProperty nrOfArtInBusket, nrOfRoles, nrOfBills;
    
    private final GUIPersonBaseData baseData;
    
    public GUIPerson(Person t, GUIPersonBaseData baseData) {
        super(t);
        
        nrOfArtInBusket = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getBusket().size();
            }

            @Override
            public void set(Integer set) {
                // inform bounded
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Artikel in Warenkorb";
            }
        });
        
        nrOfRoles = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getRoles().size();
            }

            @Override
            public void set(Integer set) {
                // inform bounded
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Anzahl Rollen";
            }
        });
        
        nrOfBills = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getBills().size();
            }

            @Override
            public void set(Integer set) {
                // inform bounded
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Anzahl Rechnungen";
            }
        });
        
        this.baseData = baseData;
    }

    public IntegerProperty getNrOfArtInBusket() {
        return nrOfArtInBusket;
    }

    public IntegerProperty getNrOfRoles() {
        return nrOfRoles;
    }

    public IntegerProperty getNrOfBills() {
        return nrOfBills;
    }

    public GUIPersonBaseData getBaseData() {
        return baseData;
    }
}
