
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.csvHandling.CSVPerson;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author simon
 */
public class GUIImportedPerson
{
    private final CSVPerson data;
    
    private final StringProperty name, prename, street, city;
    private final StringProperty salutation, title;
    private final StringProperty phoneP, phoneM, mail;
    
    private final IntegerProperty postalcode;
    
    private final IntegerProperty birthday,birthmonth,birthyear;
    
    private final ObservableList<GUIPerson> sameEntities;
    private final ObservableList<GUIPerson> updatedEntities;

    public GUIImportedPerson(CSVPerson data) {
        this.data = data;
        
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
                return data.getPhone_mobile();
            }

            @Override
            public void set(String set) {
                data.setPhone_mobile(set);
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
                return data.getPhone_landline();
            }

            @Override
            public void set(String set) {
                data.setPhone_landline(set);
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
                return data.getMail();
            }

            @Override
            public void set(String set) {
                data.setMail(set);
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
        
        postalcode = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getPlz();
            }

            @Override
            public void set(Integer set) {
                data.setPlz(set);
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
        
        birthday = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getBirthday_day();
            }

            @Override
            public void set(Integer set) {
                data.setBirthday_day(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BIRTHDAY;
            }
        });
        birthmonth = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getBirthday_month();
            }

            @Override
            public void set(Integer set) {
                data.setBirthday_month(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BIRTHMONTH;
            }
        });
        birthyear = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getBirthday_year();
            }

            @Override
            public void set(Integer set) {
                data.setBirthday_year(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return GUIStringCollection.BIRTHYEAR;
            }
        });
        
        sameEntities = FXCollections.observableArrayList();
        updatedEntities = FXCollections.observableArrayList();
    }

    public CSVPerson getData() {
        return data;
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

    public IntegerProperty getPostalcode() {
        return postalcode;
    }

    public IntegerProperty getBirthday() {
        return birthday;
    }

    public IntegerProperty getBirthmonth() {
        return birthmonth;
    }

    public IntegerProperty getBirthyear() {
        return birthyear;
    }

    public ObservableList<GUIPerson> getSameEntities() {
        return sameEntities;
    }

    public ObservableList<GUIPerson> getUpdatedEntities() {
        return updatedEntities;
    }
}
