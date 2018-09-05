
package ch.buhls.billmanager.persistance.csvHandling.data;

/**
 *
 * @author sdb
 */
public class Address
{
    String title, prename, name, street, plz, city;

    public Address(String title, String prename, String name, String street, String plz, String city)
    {
        this.title = title;
        this.prename = prename;
        this.name = name;
        this.street = street;
        this.plz = plz;
        this.city = city;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getPrename()
    {
        return prename;
    }

    public void setPrename(String prename)
    {
        this.prename = prename;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getPlz()
    {
        return plz;
    }

    public void setPlz(String plz)
    {
        this.plz = plz;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }
    
    
}
