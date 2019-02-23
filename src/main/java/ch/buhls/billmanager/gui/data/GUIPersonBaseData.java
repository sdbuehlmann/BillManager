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

    private final StringAdapterProperty name, prename, street, city, company;
    private final StringAdapterProperty salutation, title;
    private final StringAdapterProperty phoneP, phoneM, mail;
    private final StringAdapterProperty iban;

    private final IntegerAdapterProperty postalcode;

    private final ObjectAdapterProperty<LocalDate> birthday;

    public GUIPersonBaseData(PersonBaseData t) {
        super(t);

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
                return "Name";
            }
        });
        prename = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return getData().getPrename();
            }

            @Override
            public void set(String set) {
                getData().setPrename(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getStreet();
            }

            @Override
            public void set(String set) {
                getData().setStreet(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getCity();
            }

            @Override
            public void set(String set) {
                getData().setCity(set);
            }

            @Override
            public Object getBean() {
                return getData();
            }

            @Override
            public String getName() {
                return "Stadt";
            }
        });
        company = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return getData().getCompany();
            }

            @Override
            public void set(String set) {
                getData().setCompany(set);
            }

            @Override
            public Object getBean() {
                return getData();
            }

            @Override
            public String getName() {
                return "Firma";
            }
        });
        
        salutation = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return getData().getSalutation();
            }

            @Override
            public void set(String set) {
                getData().setSalutation(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getTitle();
            }

            @Override
            public void set(String set) {
                getData().setTitle(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getPhoneM();
            }

            @Override
            public void set(String set) {
                getData().setPhoneM(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getPhoneP();
            }

            @Override
            public void set(String set) {
                getData().setPhoneP(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getEmail();
            }

            @Override
            public void set(String set) {
                getData().setEmail(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getIban();
            }

            @Override
            public void set(String set) {
                getData().setIban(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getPostalcode();
            }

            @Override
            public void set(Integer set) {
                getData().setPostalcode(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                if (getData().getBirthday() == null) {
                    return null;
                }

                return getData().getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set) {
                if (set == null) {
                    getData().setBirthday(null);
                    return;
                }

                getData().setBirthday(Date.from(set.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            }

            @Override
            public Object getBean() {
                return getData();
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

    public StringProperty getCompany() {
        return company;
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

    @Override
    public void informBounded() {
        name.markInvalid();
        prename.markInvalid();
        street.markInvalid();
        city.markInvalid();
        salutation.markInvalid();
        title.markInvalid();
        phoneP.markInvalid();
        phoneM.markInvalid();
        mail.markInvalid();
        iban.markInvalid();
        postalcode.markInvalid();
        birthday.markInvalid();
    }
}
