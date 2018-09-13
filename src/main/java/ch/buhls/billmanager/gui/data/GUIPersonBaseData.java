
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.PersonBaseData;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author simon
 */
public class GUIPersonBaseData extends AGUITrackedData<PersonBaseData>
{
    
    private final StringProperty name, prename, street, city;
    private final StringProperty salutation, title;
    private final StringProperty phoneP, phoneM, mail;
    private final StringProperty iban;
    
    private final IntegerProperty postalcode;
    
    private final ObjectProperty<LocalDate> birthday;
    
    public GUIPersonBaseData(PersonBaseData t) {
        super(t);
        
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
                return "Name";
            }
        });
        prename = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getPrename();
            }

            @Override
            public void set(String set) {
                data.setPrename(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Vornamen";
            }
        });
        street = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getStreet();
            }

            @Override
            public void set(String set) {
                data.setStreet(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Strasse";
            }
        });
        city = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getCity();
            }

            @Override
            public void set(String set) {
                data.setCity(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Stadt";
            }
        });
        
        salutation = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getSalutation();
            }

            @Override
            public void set(String set) {
                data.setSalutation(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Begrüssung";
            }
        });
        title = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getTitle();
            }

            @Override
            public void set(String set) {
                data.setTitle(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Titel";
            }
        });
        
        phoneM = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getPhoneM();
            }

            @Override
            public void set(String set) {
                data.setPhoneM(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Telefonnummer M";
            }
        });
        phoneP = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getPhoneP();
            }

            @Override
            public void set(String set) {
                data.setPhoneP(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Telefonnummer P";
            }
        });
        mail = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getEmail();
            }

            @Override
            public void set(String set) {
                data.setEmail(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "E-Mail";
            }
        });
        
        iban = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getIban();
            }

            @Override
            public void set(String set) {
                data.setIban(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "IBAN";
            }
        });
        
        postalcode = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getPostalcode();
            }

            @Override
            public void set(Integer set) {
                data.setPostalcode(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "PLZ";
            }
        });
        
        birthday = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>()
        {
            @Override
            public LocalDate get() {
                if(data.getBirthday() == null){
                    return null;
                }
                
                return data.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set) {
                if(set == null){
                    data.setBirthday(null);
                    return;
                }
                
                data.setBirthday(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Geburtstag";
            }
        });
    }

    public StringProperty getName() {
        return name;
    }

    public StringProperty getPrename() {
        return prename;
    }

    public StringProperty getStreet() {
        return street;
    }

    public StringProperty getCity() {
        return city;
    }

    public StringProperty getSalutation() {
        return salutation;
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getPhoneP() {
        return phoneP;
    }

    public StringProperty getPhoneM() {
        return phoneM;
    }

    public StringProperty getMail() {
        return mail;
    }

    public StringProperty getIban() {
        return iban;
    }

    public IntegerProperty getPostalcode() {
        return postalcode;
    }

    public ObjectProperty<LocalDate> getBirthday() {
        return birthday;
    }
}
